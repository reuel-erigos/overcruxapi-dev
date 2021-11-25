package br.com.crux.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.SaldoContaContabilDTO;
import br.com.crux.infra.util.DataUtil;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilDTO;

@Component
public class MovimentacaoContabilDao extends BaseDao{
	
	
	
	public List<Integer> getContasContabeisSubordinadas(Long idPlanoConta) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" WITH RECURSIVE arvore AS (                                                                                            ");
        sql.append("         SELECT DISTINCT cc.id_categoria, cc.id_categoria_superior,                                                    ");
        sql.append("            cc.nm_categoria,                                                                                           ");
        sql.append("            cc.nm_categoria::text AS nome,                                                                             ");
        sql.append("            cc.cd_categoria_contabil::text AS cd_categoria_contabil,                                                   ");
        sql.append("            cc.st_categoria_sintetica::text AS st_categoria_sintetica,                                                 ");
        sql.append("            cc.id_instituicao                                                                                          ");
        sql.append("           FROM categorias_contabeis cc                                                                                ");
        sql.append("          WHERE 1=1                                                                                                    ");
        sql.append("            AND cc.id_categoria = :p_id_plano_conta                                                                    ");
        sql.append("        UNION ALL                                                                                                      ");
        sql.append("         SELECT DISTINCT ci.id_categoria, ci.id_categoria_superior,                                                    ");
        sql.append("             ci.nm_categoria,                                                                                          ");
        sql.append("            (arvore_1.nome || ' > '::text) || ci.nm_categoria::text AS cd,                                             ");
        sql.append("            (arvore_1.cd_categoria_contabil || '.'::text) || ci.cd_categoria_contabil::text AS cd_categoria_contabil,  ");
        sql.append("            ci.st_categoria_sintetica,                                                                                 ");
        sql.append("            ci.id_instituicao                                                                                          ");
        sql.append("           FROM categorias_contabeis ci                                                                                ");
        sql.append("             JOIN arvore arvore_1 ON ci.id_categoria_superior = arvore_1.id_categoria                                  ");
        sql.append("        )                                                                                                              ");
        sql.append("   SELECT arvore.id_categoria                                                                                          ");
        sql.append("     FROM arvore                                                                                                       ");
        sql.append("    ORDER BY arvore.cd_categoria_contabil, arvore.nome                                                                 ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("p_id_plano_conta",  idPlanoConta);
		
		@SuppressWarnings("unchecked")
		List<Object> values = query.getResultList();;
		
		List<Integer> contas = values.stream().map(p -> ((BigDecimal)p).intValue()).collect(Collectors.toList());
		return contas;
	}
	
	
	public SaldoContaContabilDTO getSaldoContaBancaria(Long idPlanoConta, LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select saldo_inicio, saldo_fim                                                                     ");
		sql.append("  from (select fn_retorna_saldo_categoria_contabil(:p_id_plano_conta, :p_dt_inicio) saldo_inicio,   ");
		sql.append("               fn_retorna_saldo_categoria_contabil(:p_id_plano_conta, :p_dt_fim) saldo_fim          ");
		sql.append("        ) as saldos                                                                                 ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("p_id_plano_conta",  idPlanoConta);
		
      	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
      	Date pDataFinal  = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
      	
		query.setParameter("p_dt_inicio", new Timestamp(DataUtil.adicionaDia(pDataInicio, -1).getTime()));
		query.setParameter("p_dt_fim", new Timestamp(pDataFinal.getTime()));		
		
		Object[] values = (Object[]) query.getSingleResult();
		return new SaldoContaContabilDTO(values);
	}
	
	
	
	public Optional<List<MovimentacaoContabilDTO>> getAllFilter(Long idCategoria, Long idPrograma, Long idProjeto, LocalDate dataInicio, LocalDate dataFim){
		StringBuilder sql = new StringBuilder();
		
		
		sql.append("	SELECT Case coalesce(po.id_programa, 0)                 		                                          			");
		sql.append("        	when 0 then pj.nm_projeto               		                                                  			");
		sql.append("	       else po.nm_programa              		                                                          			");
		sql.append("	      end as projeto_programa, 			                                                                   			");
		sql.append("	      m.nr_documento,                                                                             					");
		sql.append("	      m.dt_documento,                                                                             					");
		sql.append("	      cm.ds_categoria_movimentacao,                                                         		      			");
		sql.append("	      cm.dt_movimentacao,                                                          			               			");
  		if(Objects.nonNull(idCategoria)) {
 			sql.append("	  fn_retorna_valor_categoria_contabil(cm.id_categoria_movimentacao, coalesce(:p_id_categoria, 0)) as vl_categoria_movimentacao, 	");
  		}	
        else {
  			sql.append("	  cm.vl_categoria_movimentacao, 	");
  		}                                           
		sql.append("	      pcd.plano_conta conta_destino,                            					                                ");
		sql.append("	      pco.plano_conta conta_origem,                                                 					            ");
		sql.append("	      m.id_movimentacao,                                                                          					");
		sql.append("	      cm.id_categoria_origem,                                                                     					");
		sql.append("	      cm.id_categoria_destino,                                                                    					");
		sql.append("	      cm.id_programa,                                                                             					");
		sql.append("	      cm.id_projeto                                                                               					");
		sql.append("     FROM movimentacoes m                                                                             					");
		sql.append("       inner join categorias_movimentacoes cm on cm.id_movimentacao = m.id_movimentacao               					");
		sql.append("       inner join vw_planos_contas pco ON pco.id_categoria = cm.id_categoria_origem                   					");
		sql.append("       inner join vw_planos_contas pcd ON pcd.id_categoria = cm.id_categoria_destino                  					");
		sql.append("       left join projetos pj on pj.id_projeto = cm.id_projeto                                         					");
		sql.append("      left join programas po on po.id_programa = cm.id_programa                                       					");
		sql.append(" where 1 = 1                                                                                          					");
	
		if(Objects.nonNull(idPrograma)) {
			sql.append("  and :p_programa = cm.id_programa ");
		}

		if(Objects.nonNull(idProjeto)) {
			sql.append("  and :p_projeto = cm.id_projeto  ");
		}

  		if(Objects.nonNull(idCategoria)) {
  			sql.append(" and (cm.id_categoria_origem in (WITH RECURSIVE arvore AS (                                              ");
            sql.append("         SELECT DISTINCT cc.id_categoria                                                                 ");
            sql.append("           FROM categorias_contabeis cc                                                                  ");
            sql.append("          WHERE cc.id_categoria = :p_id_categoria                                                        ");
            sql.append("        UNION ALL                                                                                        ");
            sql.append("         SELECT DISTINCT ci.id_categoria                                                                 ");
            sql.append("           FROM categorias_contabeis ci                                                                  ");
            sql.append("             JOIN arvore arvore_1 ON ci.id_categoria_superior = arvore_1.id_categoria                    ");
            sql.append("        )                                                                                                ");
            sql.append("       SELECT arvore.id_categoria                                                                        ");
            sql.append("         FROM arvore)                                                                                    ");
			sql.append(" or cm.id_categoria_destino in (WITH RECURSIVE arvore AS (                                               ");
			sql.append("                         SELECT DISTINCT cc.id_categoria                                                 ");
			sql.append("                           FROM categorias_contabeis cc                                                  ");
			sql.append("                          WHERE cc.id_categoria = :p_id_categoria                                        ");
			sql.append("                        UNION ALL                                                                        ");
			sql.append("                         SELECT DISTINCT ci.id_categoria                                                 ");
			sql.append("                           FROM categorias_contabeis ci                                                  ");
			sql.append("                             JOIN arvore arvore_1 ON ci.id_categoria_superior = arvore_1.id_categoria    ");
			sql.append("                        )                                                                                ");
			sql.append("                       SELECT arvore.id_categoria                                                        ");
			sql.append("                         FROM arvore)                                                                    ");
			sql.append(" )                                                                                                       ");
		}                                           
		
		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND DATE_TRUNC('DAY', cm.dt_movimentacao) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )  ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("   AND DATE_TRUNC('DAY', cm.dt_movimentacao) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )  ");
		}
		
		
		sql.append(" order by pcd.plano_conta, pco.plano_conta, cm.dt_movimentacao ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(Objects.nonNull(idCategoria)) {
			query.setParameter("p_id_categoria", idCategoria);
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
		
		List<MovimentacaoContabilDTO> retorno = new ArrayList<MovimentacaoContabilDTO>();
		values.stream().forEach( colunas -> retorno.add(new MovimentacaoContabilDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
