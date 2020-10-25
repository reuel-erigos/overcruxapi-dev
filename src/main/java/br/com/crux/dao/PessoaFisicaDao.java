package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.PessoaFisicaDTO;

@Component
public class PessoaFisicaDao extends BaseDao{
	
	
	public List<PessoaFisicaDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select pf.id_pessoa_fisica,                                           ");
		sql.append("        pf.nm_pessoa_fisica,                                           ");
		sql.append("        pf.nr_cpf, pf.nm_mae,                                          ");
		sql.append("        pf.nm_pai                                                      ");
		sql.append("  from pessoas_fisicas pf                                              ");
		sql.append(" inner join instituicoes i on i.id_instituicao = pf.id_instituicao     ");
		sql.append(" where 1=1                                                             ");
		sql.append("  and i.id_instituicao = :idInstituicao                                ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<PessoaFisicaDTO> retorno = new ArrayList<PessoaFisicaDTO>();
		values.stream().forEach( colunas -> retorno.add(new PessoaFisicaDTO(colunas)));
		
		return retorno;
		
	}
	
}
