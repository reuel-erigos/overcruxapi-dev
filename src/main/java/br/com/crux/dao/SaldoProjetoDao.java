package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoDTO;

@Component
public class SaldoProjetoDao extends BaseDao{
	
	
	public Optional<List<SaldoProjetoDTO>> getAllFilter(Long idContaBancaria, Long idPrograma, Long idProjeto, LocalDate dataInicio, LocalDate dataFim){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select p.nm_programa_projeto,                             ");
		sql.append("        p.ds_fornecedor,                                   ");
		sql.append("        p.cnpj_cpf,                                        ");
		sql.append("        p.nr_documento,                                    ");
		sql.append("        p.dt_documento,                                    ");
		sql.append("        p.vl_movimentacao,                                 ");
		sql.append("        p.dt_vencimento,                                   ");
		sql.append("        p.vl_fatura,                                       ");
		sql.append("        p.nr_parcela,                                      ");
		sql.append("        p.ds_fatura,                                       ");
		sql.append("        p.nm_categoria                                     ");
		sql.append("   from vw_relatorio_faturas_pagar_periodo p               ");
		sql.append(" WHERE 1 = 1                                               ");
		
		if(Objects.nonNull(idContaBancaria)) {
			sql.append("  and :p_contaBancaria = p.cnpj_cpf  ");
		}


		if(Objects.nonNull(idPrograma)) {
			sql.append("  and :p_programa = p.idPrograma  ");
		}

		if(Objects.nonNull(idProjeto)) {
			sql.append("  and :p_projeto = p.idProjeto  ");
		}

		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_documento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )     ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_documento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )        ");
		}
		
		sql.append(" order by p.nm_programa_projeto ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		

		if(Objects.nonNull(idContaBancaria)) {
			query.setParameter("p_nm_categoria", idContaBancaria);
		}

		if(Objects.nonNull(idPrograma)) {
			query.setParameter("p_programa", idPrograma);
		}

		if(Objects.nonNull(idProjeto)) {
			query.setParameter("p_programa", idProjeto);
		}
		
		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}

		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<SaldoProjetoDTO> retorno = new ArrayList<SaldoProjetoDTO>();
		values.stream().forEach( colunas -> retorno.add(new SaldoProjetoDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
