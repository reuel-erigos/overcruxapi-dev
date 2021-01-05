package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEncaminhaAlunosCmd;
import br.com.crux.cmd.GetMotivoDesligamentoCmd;
import br.com.crux.cmd.GetNiveisTurmasCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetTiposPublicoPrioritarioCmd;
import br.com.crux.cmd.GetVulnerabilidadesAlunoCmd;
import br.com.crux.dao.dto.ComboAlunoDTO;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.TiposPublicoPrioritario;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ComboAlunoTO;

@Component
public class AlunoTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private GetVulnerabilidadesAlunoCmd getVulnerabilidadesAlunoCmd;
	@Autowired private NiveisTurmasTOBuilder niveisTurmasTOBuilder;
	@Autowired private TiposPublicoPrioritarioTOBuilder tiposPublicoPrioritarioTOBuilder;
	@Autowired private MotivoDesligamentoTOBuilder motivoDesligamentoTOBuilder;
	@Autowired private GetNiveisTurmasCmd getNiveisTurmasCmd;
	@Autowired private GetTiposPublicoPrioritarioCmd getTiposPublicoPrioritarioCmd;
	@Autowired private GetMotivoDesligamentoCmd getMotivoDesligamentoCmd;
	@Autowired private GetEncaminhaAlunosCmd encaminhaAlunosCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;

	public Aluno build(AlunoTO p) {
		Aluno retorno = new Aluno();

		BeanUtils.copyProperties(p, retorno);
		
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
		
		if(StringUtils.isEmpty(p.getMatriculaAluno())) {
			retorno.setMatriculaAluno(String.valueOf(p.getId()));
		}else {
			retorno.setMatriculaAluno(p.getMatriculaAluno());
		}

		if(Objects.isNull(p.getPessoaFisica().getCpf())) {
			retorno.getPessoasFisica().setCpf(String.valueOf(p.getId()));
		}
		
		if(Objects.nonNull(p.getNivelTurma()) && Objects.nonNull(p.getNivelTurma().getId())) {
			NiveisTurmas niveisTurmas = getNiveisTurmasCmd.getById(p.getNivelTurma().getId());
			retorno.setNivelTurma(niveisTurmas);
		}
		
		if(Objects.nonNull(p.getPrograma()) && Objects.nonNull(p.getPrograma().getId())) {
			Programa programa = getProgramaCmd.getById(p.getNivelTurma().getId());
			retorno.setPrograma(programa);
		}

		if(Objects.nonNull(p.getProjeto()) && Objects.nonNull(p.getProjeto().getId())) {
			Projeto projeto= getProjetoCmd.getById(p.getProjeto().getId());
			retorno.setProjeto(projeto);
		}
		if(Objects.nonNull(p.getMotivoDesligamento()) && Objects.nonNull(p.getMotivoDesligamento().getId())) {
			MotivoDesligamento motivoDesligamento = getMotivoDesligamentoCmd.getById(p.getMotivoDesligamento().getId());
			retorno.setMotivoDesligamento(motivoDesligamento);
		}
		
		if(Objects.nonNull(p.getTiposPublicoPrioritario()) && Objects.nonNull(p.getTiposPublicoPrioritario().getId())) {
			TiposPublicoPrioritario tiposPublicoPrioritario = getTiposPublicoPrioritarioCmd.getById(p.getTiposPublicoPrioritario().getId());
			retorno.setTiposPublicoPrioritario(tiposPublicoPrioritario);
		}
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AlunoTO buildTO(Aluno p) {
		AlunoTO retorno = new AlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
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
		retorno.setNivelTurma(niveisTurmasTOBuilder.buildTO(p.getNivelTurma()));
		retorno.setMotivoDesligamento(motivoDesligamentoTOBuilder.buildTO(p.getMotivoDesligamento()));
		retorno.setTiposPublicoPrioritario(tiposPublicoPrioritarioTOBuilder.buildTO(p.getTiposPublicoPrioritario()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		if(Objects.nonNull(p.getId())) {
			retorno.setVulnerabilidades(getVulnerabilidadesAlunoCmd.getAllAlunoTO(p.getId()));
		}
		
		if(Objects.nonNull(p.getId())) {
			retorno.setEncaminhamentos(encaminhaAlunosCmd.getAll(p.getId(), null));
		}

		return retorno;
	}

	public ComboAlunoTO buildComboTO(ComboAlunoDTO p) {
		ComboAlunoTO retorno = new ComboAlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		return retorno;
	}

	public List<AlunoTO> buildAll(List<Aluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}


	public List<ComboAlunoTO> buildAllDTO(List<ComboAlunoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

	
}
