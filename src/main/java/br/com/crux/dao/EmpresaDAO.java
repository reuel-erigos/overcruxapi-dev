package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.EmpresaDTO;

@Component
public class EmpresaDAO extends BaseDao{
	
	
	public List<EmpresaDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select e.id_empresa,                                             ");
		sql.append("        e.cd_empresa,                                             ");
		sql.append("        e.nm_razao_social,                                        ");
		sql.append("        e.nm_fantasia, e.nr_cnpj                                         ");
		sql.append("  from empresas e                                                 ");
		sql.append(" inner join instituicoes i on i.id_instituicao = e.id_instituicao ");
		sql.append(" where 1=1                                                        ");
		sql.append("  and i.id_instituicao = :idInstituicao                           ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<EmpresaDTO> retorno = new ArrayList<EmpresaDTO>();
		values.stream().forEach( colunas -> retorno.add(new EmpresaDTO(colunas)));
		
		return retorno;
		
	}
	
}
