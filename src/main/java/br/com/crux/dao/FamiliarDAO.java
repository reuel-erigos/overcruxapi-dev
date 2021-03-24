package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboFamiliarDTO;

@Component
public class FamiliarDAO extends BaseDao{
	
	
	public List<ComboFamiliarDTO> getAllComboByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select f.id_familiar,                                                          ");
		sql.append("        upper(pf.nm_pessoa_fisica)                                              ");
		sql.append("   from familiares f                                                            ");
		sql.append(" inner join pessoas_fisicas pf on pf.id_pessoa_fisica = f.id_pessoa_fisica      ");
		sql.append(" inner join alunos a on a.id_aluno = f.id_aluno                                 ");
		sql.append(" inner join unidades u on u.id_unidade = a.id_unidade                           ");
		sql.append(" inner join instituicoes inst on inst.id_instituicao = u.id_instituicao         ");
		sql.append(" where 1=1                                                                      ");
		sql.append("   and inst.id_instituicao = :idInstituicao                                     ");
		sql.append("  order by pf.nm_pessoa_fisica                                                  ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboFamiliarDTO> retorno = new ArrayList<ComboFamiliarDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboFamiliarDTO(colunas)));
		
		return retorno;
		
	}
	
}
