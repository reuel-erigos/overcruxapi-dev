package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.FornecedorColaboradorDTO;

@Component
public class FornecedorColaboradorDao extends BaseDao{
	
	
	@SuppressWarnings("unchecked")
	public List<FornecedorColaboradorDTO> getAll(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select 'Colaborador' tipo, pf.id_pessoa_fisica, upper(nm_pessoa_fisica), nr_cpf, pf.id_instituicao   ");
		sql.append("  from pessoas_fisicas pf                                                                             ");
		sql.append("    inner join funcionarios f on f.id_pessoa_fisica = pf.id_pessoa_fisica                             ");
		sql.append("   where pf.id_instituicao = :idInstituicao                                                           ");
		sql.append("  union                                                                                               ");
		sql.append(" select 'Fornecedor' tipo, pf.id_pessoa_fisica, upper(nm_pessoa_fisica), nr_cpf, pf.id_instituicao    ");
		sql.append("  from pessoas_fisicas pf                                                                             ");
		sql.append("   inner join fornecedores f on f.id_pessoa_fisica = pf.id_pessoa_fisica                              ");
		sql.append("   where pf.id_instituicao = :idInstituicao                                                           ");
		sql.append("  order by 1,3                                                                                        ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		List<Object[]> values = query.getResultList();
		
		List<FornecedorColaboradorDTO> retorno = new ArrayList<FornecedorColaboradorDTO>();
		values.stream().forEach( colunas -> retorno.add(new FornecedorColaboradorDTO(colunas)));
		
		return retorno;
	}



	
}
