package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ModuloDTO;

@Component
public class ModuloDao extends BaseDao{
	
	
	@SuppressWarnings("unchecked")
	public List<ModuloDTO> getModuloPorInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select m.id_modulo,                                       ");
		sql.append("       m.ds_modulo,                                       ");
		sql.append("       m.nm_modulo                                        ");
		sql.append("  from modulos m                                          ");
		sql.append(" where 1 = 1                                              ");
		sql.append("   and (m.modulo_pai is not null OR m.nm_modulo = 'HOME') ");
		sql.append("   and m.nm_modulo not like 'TB_REFERENCIA%'              ");
		sql.append("   and exists (                                           ");
		sql.append("                select 1                                  ");
		sql.append("                  from grupos_modulos gm                  ");
		sql.append("                 where gm.id_modulo      = m.id_modulo    ");
		sql.append("                   and gm.id_instituicao = :idInstituicao ");
		sql.append("              )	                                          ");	
		sql.append("  order by m.ds_modulo                                    ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloDTO> retorno = new ArrayList<ModuloDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloDTO(colunas)));
		
		return retorno;
	}


	@SuppressWarnings("unchecked")
	public List<ModuloDTO> getAllModulo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select m.id_modulo,                                       ");
		sql.append("       m.ds_modulo,                                       ");
		sql.append("       m.nm_modulo                                        ");
		sql.append("  from modulos m                                          ");
		sql.append(" where 1 = 1                                              ");
		sql.append("   and (m.modulo_pai is not null OR m.nm_modulo = 'HOME') ");
		sql.append("   and m.nm_modulo not like 'TB_REFERENCIA%'              ");
		sql.append("  order by m.ds_modulo                                    ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloDTO> retorno = new ArrayList<ModuloDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloDTO(colunas)));
		
		return retorno;
	}	
	
}
