package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboAlunoTrabalhandoDTO;

@Component
public class AlunoTrabalhandoDao extends BaseDao{
	
	
	public List<ComboAlunoTrabalhandoDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id_aluno_trabalhando,                                                 ");
		sql.append("        pf.nm_pessoa_fisica                                                     ");
		sql.append("  from alunos_trabalhando a                                                     ");
		sql.append(" inner join alunos aluno on aluno.id_aluno = a.id_aluno                         ");
		sql.append(" inner join pessoas_fisicas pf on aluno.id_pessoa_fisica = pf.id_pessoa_fisica  ");
		sql.append(" where 1=1                                                                      ");
		sql.append("  and pf.id_instituicao = :idInstituicao                                        ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboAlunoTrabalhandoDTO> retorno = new ArrayList<ComboAlunoTrabalhandoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboAlunoTrabalhandoDTO(colunas)));
		
		return retorno;
		
	}
	
}
