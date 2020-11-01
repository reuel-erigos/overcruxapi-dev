package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboProgramaDTO;

@Component
public class ProgramaDao extends BaseDao{
	
	
	public List<ComboProgramaDTO> getAllByIdInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct p.id_programa,                                        ");
		sql.append("        p.nm_programa                                                  ");
		sql.append("  from programas p                                                     ");
		sql.append(" inner join programas_unidades pu on pu.id_programa = p.id_programa    ");
		sql.append(" inner join unidades u on u.id_unidade = pu.id_unidade                 ");
		sql.append(" inner join instituicoes i on i.id_instituicao = u.id_instituicao      ");
		sql.append(" where 1=1                                                             ");
		sql.append("  and i.id_instituicao = :idInstituicao                                ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboProgramaDTO> retorno = new ArrayList<ComboProgramaDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboProgramaDTO(colunas)));
		
		return retorno;
		
	}
	
}
