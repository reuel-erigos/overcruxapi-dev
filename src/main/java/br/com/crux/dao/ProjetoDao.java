package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboProjetoDTO;

@Component
public class ProjetoDao extends BaseDao{
	
	public List<ComboProjetoDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select p.id_projeto                                                   ");
		sql.append("        p.nm_projeto                                                   ");
		sql.append("  from projetos p                                                      ");
		sql.append(" inner join projetos_unidades pu on pu.id_projeto = p.id_projeto       ");
		sql.append(" inner join unidades u on u.id_unidade = pu.id_unidade                 ");
		sql.append(" inner join instituicoes i on i.id_instituicao = u.id_instituicao      ");
		sql.append(" where 1=1                                                             ");
		sql.append("  and i.id_instituicao = :idInstituicao                                ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ComboProjetoDTO> retorno = new ArrayList<ComboProjetoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ComboProjetoDTO(colunas)));
		
		return retorno;
		
	}
	
}
