package br.com.crux.dao.relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.to.relatorios.gestao_pessoal.CensoDTO;

@Component
public class CensoDao extends BaseDao{
	
	
	public List<CensoDTO> get(List<Integer> listaIdsPessoaFisica) {
		StringBuilder sql = new StringBuilder();
			
		sql.append("  select  id_unidade               ,  ");
		sql.append("          id_pessoa_fisica         ,  ");
		sql.append("          id_funcao                ,  ");
		sql.append("          id_cargo                 ,  ");
		sql.append("          cd_unidade               ,  ");
		sql.append("          nm_unidade               ,  ");
		sql.append("          nm_pessoa_fisica         ,  ");
		sql.append("          dt_nascimento            ,  ");
		sql.append("          ds_sexo                  ,  ");
		sql.append("          nr_cpf                   ,  ");
		sql.append("          nr_ci                    ,  ");
		sql.append("          cd_orgao_ci              ,  ");
		sql.append("          sg_uf_ci                 ,  ");
		sql.append("          ds_email                 ,  ");
		sql.append("          ds_escolaridade          ,  ");
		sql.append("          ds_profissao             ,  ");
		sql.append("          cd_tipo_contratac        ,  ");
		sql.append("          nm_funcao                ,  ");
		sql.append("          cd_cargo                 ,  ");
		sql.append("          nm_cargo                 ,  ");
		sql.append("          qtd_carga_horaria_minima ,  ");
		sql.append("          qtd_carga_horaria_maxima ,  ");
		sql.append("          dt_admissao                 ");
		sql.append("    from vw_relatorio_censo		      ");
		sql.append(" where id_pessoa_fisica in (");
		sql.append(String.join(", ", listaIdsPessoaFisica.stream().map(String::valueOf).collect(Collectors.toList())));
		sql.append(" )");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<CensoDTO> retorno = new ArrayList<CensoDTO>();
		values.stream().forEach( colunas -> retorno.add(new CensoDTO(colunas)));
		
		return retorno;
		
	}
	
	
}
