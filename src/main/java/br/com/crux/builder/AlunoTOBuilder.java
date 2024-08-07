package br.com.crux.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.crux.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetBeneficioSocialPessoaFisicaCmd;
import br.com.crux.cmd.GetEncaminhaAlunosCmd;
import br.com.crux.cmd.GetMotivoDesligamentoCmd;
import br.com.crux.cmd.GetNiveisTurmasCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetTiposPublicoPrioritarioCmd;
import br.com.crux.cmd.GetVulnerabilidadesAlunoCmd;
import br.com.crux.dao.dto.ComboAlunoDTO;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ComboAlunoTO;
import br.com.crux.to.PessoaFisicaTO;

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
	@Autowired private GetBeneficioSocialPessoaFisicaCmd getBeneficioSocialPessoaFisicaCmd;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private AlunoContratoTOBuilder alunoContratoTOBuilder;

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
		if(Objects.nonNull(p.getPessoaFisica())) {
			retorno.setPessoasFisica(pessoaFisicaBuilder.build(p.getPessoaFisica()));
		}
		if(Objects.nonNull(p.getUnidade())) {
			retorno.setUnidade(unidadeBuilder.build(p.getUnidade()));
		}

		if(Objects.nonNull(p.getParticipaApresentacaoExterna())) {
			retorno.setParticipaApresentacaoExterna(p.getParticipaApresentacaoExterna());
		}

		retorno.setStAtivo(p.getStAtivo());

		retorno.setPaisCasados(p.getPaisCasados());

		retorno.setMoraPais(p.getMoraPais());

		if(Objects.nonNull(p.getNivelTurma()) && Objects.nonNull(p.getNivelTurma().getId())) {
			NiveisTurmas niveisTurmas = getNiveisTurmasCmd.getById(p.getNivelTurma().getId());
			retorno.setNivelTurma(niveisTurmas);
		}

		if(Objects.nonNull(p.getPrograma()) && Objects.nonNull(p.getPrograma().getId())) {
			Programa programa = getProgramaCmd.getById(p.getPrograma().getId());
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

//		Optional.ofNullable(p.getStAtivo()).ifPresent(stAtivo -> {
//			retorno.setAtivo(stAtivo.equals("true") ? "S" : "N");
//		});

//		Optional.ofNullable(p.getParticipaApresentacaoExterna()).ifPresent(participaApresentacaoExterna -> {
//			retorno.setParticipaApresentacaoExterna(participaApresentacaoExterna.equals("true") ? "S" : "N");
//		});


		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

        try {
            if (p.getAlunosContrato() != null && !p.getAlunosContrato().isEmpty()) {
                List<AlunoContrato> contratos = p.getAlunosContrato().stream().map(c ->
                {
                    c.setAluno(p);
                    return alunoContratoTOBuilder.build(c);
                }).collect(Collectors.toList());
                retorno.setAlunosContrato(contratos);
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

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
//		retorno.setstAtivo(p.getAtivo());

		retorno.setParticipaApresentacaoExterna(p.getParticipaApresentacaoExterna());

		retorno.setStAtivo(p.getStAtivo());

		retorno.setPaisCasados(p.getPaisCasados());

		retorno.setMoraPais(p.getMoraPais());

		if(Objects.nonNull(p.getId())) {
			retorno.setVulnerabilidades(getVulnerabilidadesAlunoCmd.getAllAlunoTO(p.getId()));
		}

		if(Objects.nonNull(p.getId())) {
			retorno.setEncaminhamentos(encaminhaAlunosCmd.getAll(p.getId(), null));
		}

		if(Objects.nonNull(p.getId()) && Objects.nonNull(p.getPessoasFisica())) {
			retorno.setBenefeciosSociaisPessoaFisica(getBeneficioSocialPessoaFisicaCmd.getAllPorPessoaFisicaTO(p.getPessoasFisica().getId()));
		}

		if(Objects.nonNull(p.getPrograma())) {
			retorno.setPrograma(programaTOBuilder.buildTO(p.getPrograma()));
		}
		if(Objects.nonNull(p.getProjeto())) {
			retorno.setProjeto(projetoTOBuilder.buildTO(p.getProjeto()));
		}

		if (p.getAlunosContrato() != null && !p.getAlunosContrato().isEmpty()) {
			retorno.setAlunosContrato(new ArrayList<>());
			for (AlunoContrato ac : p.getAlunosContrato())
				retorno.getAlunosContrato().add(alunoContratoTOBuilder.buildTO(ac));
		}

		return retorno;
	}

	public ComboAlunoTO buildComboTO(ComboAlunoDTO p) {
		ComboAlunoTO retorno = new ComboAlunoTO();

		if(Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);

		return retorno;
	}

	public List<AlunoTO> buildAll(List<Aluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}


	public List<ComboAlunoTO> buildAllDTO(List<ComboAlunoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

	public AlunoTO toDTOList(Aluno p) {
		AlunoTO retorno = new AlunoTO();

		if(Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setMatriculaAluno(p.getMatriculaAluno());
		retorno.setDataEntrada(p.getDataEntrada());
		retorno.setDataDesligamento(p.getDataDesligamento());
		retorno.setStAtivo(p.getStAtivo());
		retorno.setPessoaFisica(new PessoaFisicaTO());
		retorno.getPessoaFisica().setId(p.getPessoasFisica().getId());
		retorno.getPessoaFisica().setNome(p.getPessoasFisica().getNome());


		return retorno;
	}

}
