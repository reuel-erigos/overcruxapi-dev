package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetNiveisTurmasCmd;
import br.com.crux.cmd.GetVulnerabilidadesAlunoCmd;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.to.AlunoTO;

@Component
public class AlunoTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private GetVulnerabilidadesAlunoCmd getVulnerabilidadesAlunoCmd;
	@Autowired private NiveisTurmasTOBuilder niveisTurmasTOBuilder;
	@Autowired private GetNiveisTurmasCmd getNiveisTurmasCmd;

	public Aluno build(AlunoTO p) {
		Aluno retorno = new Aluno();

		retorno.setId(p.getId());
		retorno.setDescProblemaSaude(p.getDescProblemaSaude());
		retorno.setDescMedicamentosControlados(p.getDescMedicamentosControlados());
		retorno.setDescOutrasInformacoes(p.getDescOutrasInformacoes());
		retorno.setDescFormaIngressoEntidade(p.getDescFormaIngressoEntidade());
		retorno.setAtendidoOrgaoRede(p.getAtendidoOrgaoRede());
		retorno.setDataEntrada(p.getDataEntrada());
		retorno.setObservacoes(p.getObservacoes());
		retorno.setDataDesligamento(p.getDataDesligamento());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setPessoasFisica(pessoaFisicaBuilder.build(p.getPessoaFisica()));
		retorno.setUnidade(unidadeBuilder.build(p.getUnidade()));
		retorno.setDataCadastro(p.getDataCadastro()); 
		retorno.setDataAlteracaoCadastro(p.getDataAlteracaoCadastro());
		retorno.setMoraPais(p.getMoraPais());
		retorno.setPaisCasados(p.getPaisCasados());
		retorno.setMatriculadoEscPub(p.getMatriculadoEscPub());
		retorno.setDescBuscaEscola(p.getDescBuscaEscola());
		retorno.setPublicoPrioritario(p.getPublicoPrioritario());
		
		if(StringUtils.isEmpty(p.getMatriculaAluno())) {
			retorno.setMatriculaAluno(String.valueOf(p.getId()));
		}else {
			retorno.setMatriculaAluno(p.getMatriculaAluno());
		}
		
		if(Objects.nonNull(p.getNivelTurma()) && Objects.nonNull(p.getNivelTurma().getId())) {
			NiveisTurmas niveisTurmas = getNiveisTurmasCmd.getById(p.getNivelTurma().getId());
			retorno.setNivelTurma(niveisTurmas);
		}
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AlunoTO buildTO(Aluno p) {
		AlunoTO retorno = new AlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDescProblemaSaude(p.getDescProblemaSaude());
		retorno.setDescMedicamentosControlados(p.getDescMedicamentosControlados());
		retorno.setDescOutrasInformacoes(p.getDescOutrasInformacoes());
		retorno.setDescFormaIngressoEntidade(p.getDescFormaIngressoEntidade());
		retorno.setAtendidoOrgaoRede(p.getAtendidoOrgaoRede());
		retorno.setDataEntrada(p.getDataEntrada());
		retorno.setObservacoes(p.getObservacoes());
		retorno.setDataDesligamento(p.getDataDesligamento());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setPessoaFisica(pessoaFisicaBuilder.buildTO(p.getPessoasFisica()));
		retorno.setUnidade(unidadeBuilder.buildTO(p.getUnidade()));
		retorno.setDataCadastro(p.getDataCadastro()); 
		retorno.setDataAlteracaoCadastro(p.getDataAlteracaoCadastro());
		retorno.setMoraPais(p.getMoraPais());
		retorno.setPaisCasados(p.getPaisCasados());
		retorno.setMatriculadoEscPub(p.getMatriculadoEscPub());
		retorno.setDescBuscaEscola(p.getDescBuscaEscola());
		retorno.setPublicoPrioritario(p.getPublicoPrioritario());
		retorno.setMatriculaAluno(p.getMatriculaAluno());
		retorno.setNivelTurma(niveisTurmasTOBuilder.buildTO(p.getNivelTurma()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		if(Objects.nonNull(p.getId())) {
			retorno.setVulnerabilidades(getVulnerabilidadesAlunoCmd.getAllAlunoTO(p.getId()));
		}

		return retorno;
	}


	public List<AlunoTO> buildAll(List<Aluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
