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
import br.com.crux.to.relatorios.financeiro.NormativaPagamentosDTO;

@Component
public class NormativaPagamentosDao extends BaseDao{
	
	public Optional<List<NormativaPagamentosDTO>> getAllFilter(Long idCategoria, Long idEmpresa, Long idPessoaFisica, Long idPrograma, Long idProjeto, LocalDate dataInicio, LocalDate dataFim, String tipoRubrica){
		StringBuilder sql = new StringBuilder();
		
//		sql.append(" select p.nm_programa_projeto,                                                                      ");
		sql.append(" select p.nm_projeto nm_programa_projeto,                                                                      ");
		sql.append("        p.ds_fornecedor,                                                                            ");
		sql.append("        p.nr_documento,                                                                             ");
		sql.append("        p.cnpj_cpf,                                                                                 ");
		sql.append("        p.dt_documento,                                                                             ");
//		sql.append("        c.vl_total_item vl_movimentacao,                                   							");
		sql.append("        p.vl_movimentacao,                                   							");
		sql.append("        p.nr_doc_pagamento,                                                                         ");
		sql.append("        p.dt_pagamento,                                                                             ");
//		sql.append("        c.nm_categoria,																				");
		sql.append("        p.nm_categoria,																				");
		sql.append("        p.vl_pagamento,                                                                             ");
		sql.append("        p.dt_vencimento_proxima,                                                                    ");
		sql.append("        p.id_empresa,                                                                               ");
		sql.append("        p.id_pessoa_fisica,                                                                         ");
		sql.append("        p.id_programa,                                                                              ");
		sql.append("        p.id_projeto,                                                                               ");
		sql.append("        p.id_movimentacao                                                                           ");
//		sql.append("   from vw_relatorio_relacao_nominativa_pagamentos p                                                ");
		sql.append("   from vw_relacao_nominativa_pagamentos p                                                ");
//		sql.append("   inner join vw_categorias_itens_movimentacoes c on p.id_movimentacao = c.id_movimentacao          ");
//		sql.append(" WHERE c.cd_tipo_categoria = :p_tipo_rubrica                                                        ");
		sql.append(" WHERE p.cd_tipo_categoria = :p_tipo_rubrica                                                        ");
//		sql.append("   and (c.id_categoria = :p_id_categoria or :p_id_categoria is null)                                                        ");
	    
	
		
		if(Objects.nonNull(idEmpresa)) {
			sql.append("  and :p_empresa = p.id_empresa  ");
		}

		if(Objects.nonNull(idPessoaFisica)) {
			sql.append("  and :p_pessoa_fisica = p.id_pessoa_fisica  ");
		}

		if(Objects.nonNull(idPrograma)) {
			sql.append("  and :p_programa = p.id_programa  ");
		}

		if(Objects.nonNull(idProjeto)) {
			sql.append("  and :p_projeto = p.id_projeto  ");
		}

/*
  		if(Objects.nonNull(idCategoria)) {
			sql.append(" and (p.id_movimentacao in (select c.id_movimentacao   ");
			sql.append("	from vw_rateio_itens_movimentacoes_categorias c    ");
			sql.append("	where c.id_categoria = :p_id_categoria)            ");
			sql.append("	or :p_id_categoria is null)                        ");
		}                                           
*/		
  		if(Objects.nonNull(idCategoria)) {
			sql.append(" and (c.id_categoria in (select ci.id_categoria   ");
//			sql.append("	from vw_categorias_itens_movimentacoes ci    ");
			sql.append("	from vw_categorias_relacao_nominativa_pagamentos ci    ");
			sql.append("	where ci.id_categoria = :p_id_categoria))            ");
		}                                           
  			
		
				

		
		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND to_date( p.dt_documento, 'dd/mm/yyyy') >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )  ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("   AND to_date( p.dt_documento, 'dd/mm/yyyy') <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )  ");
		}
		sql.append(" order by p.nm_programa_projeto ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		
		query.setParameter("p_tipo_rubrica", "A".toUpperCase().equals(tipoRubrica) ? "A" : "N");

		if(Objects.nonNull(idCategoria)) {
			query.setParameter("p_id_categoria", idCategoria);
		}
		
		if(Objects.nonNull(idEmpresa)) {
			query.setParameter("p_empresa", idEmpresa);
		}

		if(Objects.nonNull(idPessoaFisica)) {
			query.setParameter("p_pessoa_fisica", idPessoaFisica);
		}

		if(Objects.nonNull(idPrograma)) {
			query.setParameter("p_programa", idPrograma);
		}

		if(Objects.nonNull(idProjeto)) {
			query.setParameter("p_projeto", idProjeto);
		}

		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<NormativaPagamentosDTO> retorno = new ArrayList<NormativaPagamentosDTO>();
		values.stream().forEach( colunas -> retorno.add(new NormativaPagamentosDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
