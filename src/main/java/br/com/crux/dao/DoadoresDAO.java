package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboDoadoresDTO;

@Component
public class DoadoresDAO extends BaseDao{
	
	
	public List<ComboDoadoresDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select d.id_doador,                                                         ");
		sql.append("        pf.nm_pessoa_fisica                                                  ");
		sql.append("  from doadores d                                                            ");
		sql.append(" inner join pessoas_fisicas pf on d.id_pessoa_fisica = pf.id_pessoa_fisica   ");
		sql.append(" where 1=1                                                                   ");
		sql.append("  and d.id_instituicao = :idInstituicao                                      ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboDoadoresDTO> retorno = new ArrayList<ComboDoadoresDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboDoadoresDTO(colunas)));
		
		return retorno;
		
	}
	
}
