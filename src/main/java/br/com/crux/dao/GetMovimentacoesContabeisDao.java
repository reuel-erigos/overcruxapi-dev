package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.MovimentacoesContabeisDTO;
import br.com.crux.infra.util.Java8DateUtil;

@Component
public class GetMovimentacoesContabeisDao extends BaseDao {

	
	@SuppressWarnings("unchecked")
	public List<MovimentacoesContabeisDTO> getAllFilter(Long idPrograma, Long idProjeto, Double valor, LocalDate dataInicio, LocalDate dataFim, Long idContaDestino, Long idContaOrigem, Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select mc.id_movimentacao_contabil,                                                  ");
		sql.append("      mc.dt_movimentacao,                                                             ");
		sql.append("      mc.vl_movimentacao,                                                             ");
		sql.append("      mc.id_programa_1,                                                               ");
		sql.append("      pg1.nm_programa nomePrograma01,                                                 ");
		sql.append("      mc.id_projeto_1,                                                                ");
		sql.append("      pj1.nm_projeto nomeProjeto01,                                                   ");
		sql.append("      mc.id_categoria_origem_1,                                                       ");
		sql.append("      co1.plano_conta contaOrigem01,                                                  ");
		sql.append("      co1.cd_categoria_contabil cdContaOrigem01,                                      ");
		sql.append("      mc.id_categoria_destino_1,                                                      ");
		sql.append("      cd1.plano_conta contaDestinoPlanoConta01,                                       ");
		sql.append("      cd1.cd_categoria_contabil contaDestinoCodigoCatContb01,                         ");
		sql.append("      mc.id_programa_2,                                                               ");
		sql.append("      pg2.nm_programa nomePrograma02,                                                 ");
		sql.append("      mc.id_projeto_2,                                                                ");
		sql.append("      pj2.nm_projeto nomeProjeto02,                                                   ");
		sql.append("      mc.id_categoria_origem_2,                                                       ");
		sql.append("      co2.plano_conta contaOrigem02,                                                  ");
		sql.append("      co2.cd_categoria_contabil cdContaOrigem02,                                      ");
		sql.append("      mc.id_categoria_destino_2,                                                      ");
		sql.append("      cd2.plano_conta contaDestinoPlanoConta02,                                       ");
		sql.append("      cd2.cd_categoria_contabil contaDestinoCodigoCatContb02                          ");
		sql.append(" from  movimentacoes_contabeis mc                                                     ");
		sql.append("   inner join vw_planos_contas co1 on mc.id_categoria_origem_1  = co1.id_categoria    ");
		sql.append("   inner join vw_planos_contas cd1 on mc.id_categoria_destino_1 = cd1.id_categoria    ");
		sql.append("   inner join vw_planos_contas co2 on mc.id_categoria_origem_2  = co2.id_categoria    ");
		sql.append("   inner join vw_planos_contas cd2 on mc.id_categoria_destino_2 = cd2.id_categoria    ");
		sql.append("   left join programas pg1 on pg1.id_programa = mc.id_programa_1                      ");
		sql.append("   left join projetos pj1 on pj1.id_projeto    = mc.id_projeto_1                      ");
		sql.append("   left join programas pg2 on pg2.id_programa = mc.id_programa_2                      ");
		sql.append("   left join projetos pj2 on pj2.id_projeto    = mc.id_projeto_2                      ");
		sql.append(" where 1 = 1                                                                          ");
  
		if(Objects.nonNull(valor)) {
			sql.append("and mc.dt_movimentacao        = :p_valor");
		}
		
		if(Objects.nonNull(idContaOrigem)) {
			sql.append(" and mc.id_categoria_origem_1  = :p_categoria_origem");
		    //sql.append(" and mc.id_categoria_origem_2  = :p_categoria_origem");
		}
		
		if(Objects.nonNull(idContaDestino)) {
			sql.append(" and mc.id_categoria_destino_1 = :p_categoria_destino");
			//sql.append(" and mc.id_categoria_destino_2 = :p_categoria_destino");
		}
		
		if(Objects.nonNull(dataInicio)) {
			sql.append(" AND DATE_TRUNC('DAY', mc.dt_movimentacao) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )");
		}
	    
		if(Objects.nonNull(dataFim)) {
			sql.append(" and DATE_TRUNC('DAY', mc.dt_movimentacao) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )");
		}
		
		
		sql.append(" and mc.id_instituicao = " + idInstituicao);
		
		sql.append(" order by mc.dt_movimentacao desc");

		Query query = em.createNativeQuery(sql.toString());
		
		if(Objects.nonNull(valor)) {
			query.setParameter("p_valor", valor);
		}

		if(Objects.nonNull(idContaOrigem)) {
			query.setParameter("p_categoria_origem", idContaOrigem);
		}
		
		if(Objects.nonNull(idContaDestino)) {
			query.setParameter("p_categoria_destino", idContaDestino);
		}

		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}

		
		List<Object[]> values = query.getResultList();
		
		List<MovimentacoesContabeisDTO> retorno = new ArrayList<MovimentacoesContabeisDTO>();
		values.stream().forEach( colunas -> retorno.add(new MovimentacoesContabeisDTO(colunas)));
		
		return retorno;
	}

	
}
