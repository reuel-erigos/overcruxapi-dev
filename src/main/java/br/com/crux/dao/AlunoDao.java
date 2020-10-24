package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.AlunoDTO;

@Component
public class AlunoDao extends BaseDao{
	
	
	public List<AlunoDTO> getAllByUnidade(Long idUnidade) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id_aluno,                                                           ");
		sql.append("        pf.nm_pessoa_fisica                                                   ");
		sql.append("  from alunos a                                                               ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append(" where 1=1                                                                    ");
		sql.append("  and a.id_unidade = :idUnidade                                               ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUnidade", idUnidade);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AlunoDTO> retorno = new ArrayList<AlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new AlunoDTO(colunas)));
		
		return retorno;
		
	}
	
}
