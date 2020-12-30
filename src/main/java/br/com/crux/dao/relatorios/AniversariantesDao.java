package br.com.crux.dao.relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.to.relatorios.gestao_pessoal.AniversariantesDTO;

@Component
public class AniversariantesDao extends BaseDao{
	
	
	public List<AniversariantesDTO> get(List<Integer> listaIdsPessoaFisica) {
		StringBuilder sql = new StringBuilder();
			
		sql.append("  select id_unidade,                          ");
		sql.append("         id_pessoa_fisica,                    ");
		sql.append("         cd_unidade,                          ");
		sql.append("         nm_unidade,                          ");
		sql.append("         nm_pessoa_fisica,                    ");
		sql.append("         dt_nascimento,                       ");
		sql.append("         mes                                  ");
		sql.append("    from vw_relatorio_aniversario_colaborador ");		
		sql.append(" where id_pessoa_fisica in (");
		sql.append(String.join(", ", listaIdsPessoaFisica.stream().map(String::valueOf).collect(Collectors.toList())));
		sql.append(" )");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AniversariantesDTO> retorno = new ArrayList<AniversariantesDTO>();
		values.stream().forEach( colunas -> retorno.add(new AniversariantesDTO(colunas)));
		
		return retorno;
		
	}
	
	
}
