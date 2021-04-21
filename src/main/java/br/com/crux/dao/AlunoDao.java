package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboAlunoDTO;

@Component
public class AlunoDao extends BaseDao{
	
	
	public List<ComboAlunoDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id_aluno,                                                           ");
		sql.append("        pf.nm_pessoa_fisica,                                                  ");
		sql.append("        a.nr_matricula_aluno,                                                 ");
		sql.append("        a.dt_entrada,                                                         ");
		sql.append("        a.dt_desligamento                                                     ");
		sql.append("  from alunos a                                                               ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append(" where 1=1                                                                    ");
		sql.append("  and pf.id_instituicao = :idInstituicao                                      ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboAlunoDTO> retorno = new ArrayList<ComboAlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboAlunoDTO(colunas)));
		
		return retorno;
		
	}
	
}
