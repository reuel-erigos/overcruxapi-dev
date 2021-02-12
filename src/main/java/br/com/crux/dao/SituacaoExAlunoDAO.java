package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboSituacaoExAlunoDTO;

@Component
public class SituacaoExAlunoDAO extends BaseDao{
	
	
	public List<ComboSituacaoExAlunoDTO> getAll(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select sea.id_situacao_aluno, pf.nm_pessoa_fisica                         ");
		sql.append("  from situacoes_ex_alunos sea                                             ");
		sql.append(" inner join alunos a on sea.id_aluno = a.id_aluno                          ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica ");
		sql.append(" inner join unidades uni on uni.id_unidade = a.id_unidade                  ");
		sql.append(" where 1=1                                                                 ");
		sql.append(" and uni.id_instituicao = :idInstituicao                                   ");
		sql.append(" order by pf.nm_pessoa_fisica ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboSituacaoExAlunoDTO> retorno = new ArrayList<ComboSituacaoExAlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboSituacaoExAlunoDTO(colunas)));
		
		return retorno;
		
	}

	
}
