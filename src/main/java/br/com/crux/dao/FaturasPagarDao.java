package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.infra.util.NumeroUtil;
import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;

@Component
public class FaturasPagarDao extends BaseDao{
	
	
	public Optional<List<FaturasPagarDTO>> getAllFilter(String categoria, String cnpjCpf, String programaProjeto, 
			                                                          LocalDate dataInicio, LocalDate dataFim,
			                                                          LocalDate dataInicioVenc, LocalDate dataFimVenc){
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
		sql.append("   from vw_relatorio_faturas_pagar_periodo p             ");
		sql.append(" WHERE 1 = 1                                                                                                                        ");
		
		if(StringUtils.isNotEmpty(cnpjCpf)) {
			sql.append("  and :p_cnpj_cpf = p.cnpj_cpf  ");
		}

		if(StringUtils.isNotEmpty(categoria)) {
			sql.append("  and :p_nm_categoria = p.nm_categoria  ");
		}

		if(StringUtils.isNotEmpty(programaProjeto)) {
			sql.append("  and :p_programaProjeto = p.nm_programa_projeto  ");
		}

		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_documento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )     ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_documento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )        ");
		}
		
		if(Objects.nonNull(dataInicioVenc)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_vencimento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_venc ,'dd/mm/yyyy') )     ");
		}
		
		if(Objects.nonNull(dataFimVenc)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_vencimento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_venc ,'dd/mm/yyyy') )        ");
		}
		
		sql.append(" order by p.nm_programa_projeto ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(StringUtils.isNotEmpty(cnpjCpf)) {
			query.setParameter("p_cnpj_cpf", NumeroUtil.extrairNumerosMatches(cnpjCpf));
		}

		if(StringUtils.isNotEmpty(categoria)) {
			query.setParameter("p_nm_categoria", categoria);
		}

		if(StringUtils.isNotEmpty(categoria)) {
			query.setParameter("p_programaProjeto", programaProjeto);
		}
		
		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}

		if(Objects.nonNull(dataInicioVenc)) {
			query.setParameter("p_dt_inicio_venc", Java8DateUtil.getLocalDateFormater(dataInicioVenc));
		}
		
		if(Objects.nonNull(dataFimVenc)) {
			query.setParameter("p_dt_fim_venc", Java8DateUtil.getLocalDateFormater(dataFimVenc));
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<FaturasPagarDTO> retorno = new ArrayList<FaturasPagarDTO>();
		values.stream().forEach( colunas -> retorno.add(new FaturasPagarDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
