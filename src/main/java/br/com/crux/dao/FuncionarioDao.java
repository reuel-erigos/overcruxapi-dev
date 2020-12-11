package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.FuncionarioDTO;

@Component
public class FuncionarioDao extends BaseDao{
	
	
	public List<FuncionarioDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select f.id_funcionario,                                                     ");
		sql.append("        pf.nm_pessoa_fisica                                                   ");
		sql.append("  from funcionarios f                                                         ");
		sql.append(" inner join pessoas_fisicas pf on f.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append(" where 1=1                                                                    ");
		sql.append("  and pf.id_instituicao = :idInstituicao                                      ");
		sql.append(" order by pf.nm_pessoa_fisica                                                 ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<FuncionarioDTO> retorno = new ArrayList<FuncionarioDTO>();
		values.stream().forEach( colunas -> retorno.add(new FuncionarioDTO(colunas)));
		
		return retorno;
		
	}
	
}
