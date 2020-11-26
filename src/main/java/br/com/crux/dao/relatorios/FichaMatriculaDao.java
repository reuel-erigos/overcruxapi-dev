package br.com.crux.dao.relatorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.to.relatorios.FichaMatriculaDTO;

@Component
public class FichaMatriculaDao extends BaseDao{
	
	
	public List<FichaMatriculaDTO> get() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select                                                                      ");
		sql.append("        id_aluno idAluno,                                                    ");
		sql.append("        id_pessoa_fisica idPessoaFisica,                                     ");
		sql.append("        nr_matricula_aluno numeroMatriculaAluno,                             ");
		sql.append("        nome nome,                                                           ");
		sql.append("        dt_nascimento dataNascimento,                                        ");
		sql.append("        nr_cpf numeroCpf,                                                    ");
		sql.append("        ds_endereco endereco,                                                ");
		sql.append("        ds_bairro bairro,                                                    ");
		sql.append("        sg_uf_endereco ufEndereco,                                           ");
		sql.append("        nm_mae nomeMae,                                                      ");
		sql.append("        nm_pai nomePai,                                                      ");
		sql.append("        nr_cep cep,                                                          ");
		sql.append("        nr_fone_celular foneCelular,                                         ");
		sql.append("        nr_telefone_residencial telefoneResidencial,                         ");
		sql.append("        nr_telefone_comercial telefoneComercial,                             ");
		sql.append("        nome_responsavel nomeResponsavel,                                    ");
		sql.append("        dt_nascimento_responsavel dataNascimentoResponsavel,                 ");
		sql.append("        nr_cpf_responsavel numeroCpfResponsavel,                             ");
		sql.append("        rg_responsavel rgResponsavel,                                        ");
		sql.append("        nm_grau_parentesco grauParentesco,                                   ");
		sql.append("        fone_celular_responsavel foneCelularResponsavel,                     ");
		sql.append("        fone_residencial_responsavel foneResidencialResponsavel,             ");
		sql.append("        fone_comercial_responsavel foneComercialResponsavel,                 ");
		sql.append("        cd_unidade codigoUnidade,                                            ");
		sql.append("        programa programa,                                                   ");
		sql.append("        dias_semana diasSemana,                                              ");
		sql.append("        hr_inicio horaInicio,                                                ");
		sql.append("        hr_fim horaFim,                                                      ");
		sql.append("        st_apr_externa_pub apresentacaoExterna,                              ");
		sql.append("        dt_matricula dataMatricula,                                          ");
		sql.append("        ds_curso_escola cursoEscola,                                         ");
		sql.append("        ds_escola escola,                                                    ");
		sql.append("        ds_escolaridade escolaridade,                                        ");
		sql.append("        tx_observacao_declaracao_matricula observacaoDeclaracaoMatricula,    ");
		sql.append("        dt_declaracao_matricula dataDeclaracaoMatricula                      ");
		sql.append(" from vw_relatorio_declaracao_matricula                                      ");
		
		
		
		Query query = em.createNativeQuery(sql.toString());
		//query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<FichaMatriculaDTO> retorno = new ArrayList<FichaMatriculaDTO>();
		values.stream().forEach( colunas -> retorno.add(new FichaMatriculaDTO(colunas)));
		
		return retorno;
		
	}
	
}
