package br.com.crux.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.MovimentoExtratoDTO;

@Component
public class ExtratoDao  extends BaseDao {

	public ExtratoDao() {
	}
	

	@SuppressWarnings("unchecked")
	public List<MovimentoExtratoDTO> getMovimentosExtrato(Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT m.nr_documento,                                                                                        ");
		sql.append("        m.ds_movimentacao,                                                                                     ");
		sql.append("        p.dt_pagamento as dt_movimentacao,                                                                     ");
		sql.append("        null as vl_entrada,                                                                                    ");
		sql.append("        ((COALESCE(p.vl_pagamento,0) + COALESCE(p.vl_multa,0) + COALESCE(p.vl_juros,0))) as vl_saida     ");
		sql.append("  FROM pagamentos_faturas p, faturas_movimentacoes f, movimentacoes m                                          ");
		sql.append("   WHERE p.id_fatura = f.id_fatura                                                                             ");
		sql.append("     AND f.id_movimentacao = m.id_movimentacao                                                                 ");
		sql.append("     AND p.id_conta_bancaria = :p_id_conta_bancaria                                                            ");
		sql.append("     AND  DATE_TRUNC('DAY', p.dt_pagamento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy'))         ");
		sql.append("     AND  DATE_TRUNC('DAY', p.dt_pagamento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy'))            ");
		sql.append("   UNION                                                                                                       ");
		sql.append("   SELECT m.nr_documento,                                                                                      ");
		sql.append("          m.ds_movimentacao,                                                                                   ");
		sql.append("          m.dt_documento as dt_movimentacao,                                                                   ");
		sql.append("          (COALESCE(m.vl_movimentacao,0) + COALESCE(m.vl_iss,0) + COALESCE(m.vl_icms,0) + COALESCE(m.vl_ipi,0) ");
		sql.append("          + COALESCE(m.vl_pis_cofins_csll,0) + COALESCE(m.vl_inss,0))  as vl_entrada,                          ");
		sql.append("          null as vl_saida                                                                                     ");
		sql.append("     FROM movimentacoes m                                                                                      ");
		sql.append("    WHERE m.id_conta_bancaria = :p_id_conta_bancaria                                                           ");
		sql.append("      AND COALESCE(m.st_tipo_movimentacao, 'O') = 'E'                                                          ");
		sql.append("      AND DATE_TRUNC('DAY', m.dt_documento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )        ");
		sql.append("      AND DATE_TRUNC('DAY', m.dt_documento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )           ");
		sql.append("   ORDER  BY dt_movimentacao, vl_entrada desc, vl_saida                                                        ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("p_id_conta_bancaria", idContaBancaria);
		query.setParameter("p_dt_inicio", dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		query.setParameter("p_dt_fim", dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		List<Object[]> values = query.getResultList();
		
		List<MovimentoExtratoDTO> retorno = new ArrayList<MovimentoExtratoDTO>();
		values.stream().forEach( colunas -> retorno.add(new MovimentoExtratoDTO(colunas)));
		
		return retorno;
	}


	public Double getValorSaldoAnterior(Long idContaBancaria, LocalDate data) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT COALESCE(s.vl_saldo,0) as vl_saldo                                                                                             ");
	    sql.append("   FROM saldos_contas_bancarias s                                                                                                      ");
	    sql.append("  WHERE id_conta_bancaria = :p_id_conta_bancaria                                                                                       ");
	    sql.append("    AND DATE_TRUNC('DAY', s.dt_saldo) =  (SELECT max(DATE_TRUNC('DAY', si.dt_saldo))                                                   ");
	    sql.append("                                            FROM saldos_contas_bancarias si                                                            ");
	    sql.append("                                           WHERE si.id_conta_bancaria = s.id_conta_bancaria                                            ");
	    sql.append("                                             AND DATE_TRUNC('DAY', si.dt_saldo) < DATE_TRUNC('DAY', to_date( :p_data ,'dd/mm/yyyy') )) ");

		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("p_id_conta_bancaria", idContaBancaria);
		query.setParameter("p_data",  data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		Double valorSaldo = 0.0;
		try {
			Object values = query.getSingleResult();
			if(values != null) {
				valorSaldo = ((BigDecimal)values).doubleValue();
			}
		} catch (NoResultException e) {
			valorSaldo = 0.0;
		}
		
		
		return valorSaldo;
	}


	
	public Double getValorSaldoAtual(Long idContaBancaria, LocalDate data) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT COALESCE(s.vl_saldo,0) as vl_saldo                                                                                             ");
	    sql.append("   FROM saldos_contas_bancarias s                                                                                                      ");
	    sql.append("  WHERE id_conta_bancaria = :p_id_conta_bancaria                                                                                       ");
	    sql.append("    AND DATE_TRUNC('DAY', s.dt_saldo) =  (SELECT max(DATE_TRUNC('DAY', si.dt_saldo))                                                   ");
	    sql.append("                                            FROM saldos_contas_bancarias si                                                            ");
	    sql.append("                                           WHERE si.id_conta_bancaria = s.id_conta_bancaria                                            ");
	    sql.append("                                             AND DATE_TRUNC('DAY', si.dt_saldo) <= DATE_TRUNC('DAY', to_date( :p_data ,'dd/mm/yyyy') )) ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("p_id_conta_bancaria", idContaBancaria);
		query.setParameter("p_data",  data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		Double valorSaldo = 0.0;
		try {
			Object values = query.getSingleResult();
			if(values != null) {
				valorSaldo = ((BigDecimal)values).doubleValue();
			}
		} catch (NoResultException e) {
			valorSaldo = 0.0;
		}
		
		
		return valorSaldo;
	}	
}
