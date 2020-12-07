package br.com.crux.dao.relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.to.relatorios.beneficiarios.PasseEstudantilBeneficiarioDTO;

@Component
public class PasseEstudantilBeneficiarioDao extends BaseDao{
	
	public List<PasseEstudantilBeneficiarioDTO> get(List<Integer> listaIdsPessoaFisica) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("	select                                              ");
		sql.append("			id_aluno                                ,   ");
		sql.append("			id_pessoa_fisica                        ,   ");
		sql.append("			nr_matricula_aluno                      ,   ");
		sql.append("			nome                                    ,   ");
		sql.append("			dt_nascimento                           ,   ");
		sql.append("			nr_cpf                                  ,   ");
		sql.append("			ds_endereco                             ,   ");
		sql.append("			ds_bairro                               ,   ");
		sql.append("			sg_uf_endereco                          ,   ");
		sql.append("			nm_mae                                  ,   ");
		sql.append("			nm_pai                                  ,   ");
		sql.append("			nr_cep                                  ,   ");
		sql.append("			nr_fone_celular                         ,   ");
		sql.append("			nr_telefone_residencial                 ,   ");
		sql.append("			nr_telefone_comercial                   ,   ");
		sql.append("			nome_responsavel                        ,   ");
		sql.append("			dt_nascimento_responsavel               ,   ");
		sql.append("			nr_cpf_responsavel                      ,   ");
		sql.append("			rg_responsavel                          ,   ");
		sql.append("			nm_grau_parentesco                      ,   ");
		sql.append("			fone_celular_responsavel                ,   ");
		sql.append("			fone_residencial_responsavel            ,   ");
		sql.append("			fone_comercial_responsavel              ,   ");
		sql.append("			cd_unidade                              ,   ");
		sql.append("			programa                                ,   ");
		sql.append("			dias_semana                             ,   ");
		sql.append("			hr_inicio                               ,   ");
		sql.append("			hr_fim                                  ,   ");
		sql.append("			st_apr_externa_pub                      ,   ");
		sql.append("			dt_matricula                            ,   ");
		sql.append("			ds_curso_escola                         ,   ");
		sql.append("			ds_escola                               ,   ");
		sql.append("			ds_escolaridade                         ,   ");
		sql.append("			tx_observacao_declaracao_matricula      ,   ");
		sql.append("			dt_declaracao_passe                         ");
		sql.append("	from vw_relatorio_declaracao_matricula              ");
		sql.append(" where id_pessoa_fisica in (");
		sql.append(String.join(", ", listaIdsPessoaFisica.stream().map(String::valueOf).collect(Collectors.toList())));
		sql.append(" )");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<PasseEstudantilBeneficiarioDTO> retorno = new ArrayList<PasseEstudantilBeneficiarioDTO>();
		values.stream().forEach( colunas -> retorno.add(new PasseEstudantilBeneficiarioDTO(colunas)));
		
		return retorno;
	}
	
	
}
