package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.MenuDTO;

@Component
public class MenuDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<MenuDTO> getMenuPrincipal(Long idUsuario, Long idUnidade) {
		StringBuilder sql = new StringBuilder();
		
		/*
		sql.append(" with recursive home (id_modulo_pai, modulo_pai, id_modulo_filho, modulo_filho, nivel) as(    ");
		sql.append(" 	  	select m.id_modulo, m.nm_modulo, m.id_modulo, m.nm_modulo, 'PAI'                      ");
		sql.append(" 	      from usuarios_grupos ug,                                                            ");
		sql.append(" 	           usuarios_sistema us,                                                           ");
		sql.append(" 	           modulos m,                                                                     ");
		sql.append(" 	           grupos_modulos gm                                                              ");
		sql.append(" 	     where ug.id_grupo_modulo  = gm.id_grupo_modulo                                       ");
		sql.append(" 	       and gm.id_modulo        = m.id_modulo                                              ");
		sql.append(" 	       and ug.id_usuario       = us.id_usuario                                            ");
		sql.append(" 	       and us.st_ativo         = 'S'                                                      ");
		sql.append(" 	       and gm.id_unidade       = :idUnidade                                               ");
		sql.append(" 	       and us.id_usuario       = :idUsuario                                               ");
		sql.append(" 	  	   and m.modulo_pai        is null                                                    ");
		sql.append(" 	  	union all                                                                             ");
        sql.append("        	select filho.id_modulo, filho.nm_modulo, pai.id_modulo, pai.nm_modulo, 'FILHO'    ");
        sql.append("        	  from modulos as pai,                                                            ");
        sql.append("        	       modulos as filho,                                                          ");
        sql.append("                 usuarios_grupos ug,                                                          ");
        sql.append("                 usuarios_sistema us,                                                         ");
        sql.append("                 grupos_modulos gm   	                                                      ");
        sql.append("          where pai.modulo_pai = filho.id_modulo                                              ");
        sql.append("            and ug.id_grupo_modulo  = gm.id_grupo_modulo                                      ");
        sql.append("            and gm.id_modulo        = filho.id_modulo                                         ");
        sql.append("            and ug.id_usuario       = us.id_usuario                                           ");
        sql.append("            and us.st_ativo         = 'S'                                                     ");
        sql.append("            and gm.id_unidade       = :idUnidade                                              ");
        sql.append("            and us.id_usuario       = :idUsuario                                              ");
	    sql.append("            and pai.modulo_pai    is not null    		                                      ");
		sql.append(" 	  )                                                                                       ");
		sql.append(" 	 select * from home                                                                       ");
		sql.append(" 	  order by 2,5 desc                                                                       ");
		*/
		
		sql.append(" select m.id_modulo, m.nm_modulo                        ");
		sql.append("   from usuarios_sistema us                             ");
		sql.append("        inner join usuarios_grupos  ug                  ");
		sql.append("             on ug.id_usuario = us.id_usuario           ");
		sql.append("        inner join grupos_modulos   gm                  ");
		sql.append("             on ug.id_grupo_modulo = gm.id_grupo_modulo ");
		sql.append("        inner join modulos m                            ");
		sql.append("             on gm.id_modulo  = m.id_modulo             ");
		sql.append("  where gm.id_unidade      = :idUnidade                 ");
		sql.append("    and us.st_ativo        = 'S'                        ");
		sql.append("    and us.id_usuario      = :idUsuario                 ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("idUnidade", idUnidade);
		
		List<Object[]> values = query.getResultList();
		
		List<MenuDTO> retorno = new ArrayList<MenuDTO>();
		values.stream().forEach( colunas -> retorno.add(new MenuDTO(colunas)));
		
		return retorno;
	}
	

}
