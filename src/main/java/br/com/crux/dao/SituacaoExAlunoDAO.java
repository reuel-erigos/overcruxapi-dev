package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboSituacaoExAlunoDTO;

@Component
public class SituacaoExAlunoDAO extends BaseDao{
	
	
	public List<ComboSituacaoExAlunoDTO> getAll() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sea.id_situacao_aluno, pf.nm_pessoa_fisica                         ");
		sql.append("  from situacoes_ex_alunos sea                                             ");
		sql.append(" inner join alunos a on sea.id_aluno = a.id_aluno                          ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica ");
		sql.append(" where 1=1                                                                 ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboSituacaoExAlunoDTO> retorno = new ArrayList<ComboSituacaoExAlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboSituacaoExAlunoDTO(colunas)));
		
		return retorno;
		
	}

	
}
