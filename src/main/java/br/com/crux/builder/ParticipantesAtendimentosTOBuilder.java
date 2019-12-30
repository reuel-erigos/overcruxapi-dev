package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtendimentosCmd;
import br.com.crux.cmd.GetFamiliaresCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.Atendimentos;
import br.com.crux.entity.Familiares;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.ParticipantesAtendimentos;
import br.com.crux.to.ParticipantesAtendimentosTO;

@Component
public class ParticipantesAtendimentosTOBuilder {

	@Autowired private AtendimentosTOBuilder atendimentosBuilder;
	@Autowired private FamiliaresTOBuilder familiarBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioBuilder;
	@Autowired private GetFamiliaresCmd getFamiliaresCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetAtendimentosCmd getAtendimentosCmd;

	public ParticipantesAtendimentos build(ParticipantesAtendimentosTO p) {
		ParticipantesAtendimentos retorno = new ParticipantesAtendimentos();

		retorno.setId(p.getId());

		Optional.ofNullable(p.getFamiliar()).ifPresent(fam -> {
			if (Objects.nonNull(fam.getId())) {
				Familiares familiares = getFamiliaresCmd.getById(fam.getId());
				retorno.setFamiliar(familiares);
			}
		});

		Optional.ofNullable(p.getFuncionario()).ifPresent(fam -> {
			Funcionario funcionario = getFuncionarioCmd.getById(fam.getId());
			retorno.setFuncionario(funcionario);
		});

		Optional.ofNullable(p.getAtendimento()).ifPresent(fam -> {
			Atendimentos atendimentos = getAtendimentosCmd.getById(fam.getId());
			retorno.setAtendimento(atendimentos);
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;

	}

	public ParticipantesAtendimentosTO buildTO(ParticipantesAtendimentos p) {
		ParticipantesAtendimentosTO retorno = new ParticipantesAtendimentosTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setFamiliar(familiarBuilder.buildTO(p.getFamiliar()));
		retorno.setFuncionario(funcionarioBuilder.buildTO(p.getFuncionario()));
		retorno.setAtendimento(atendimentosBuilder.buildTO(p.getAtendimento()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<ParticipantesAtendimentosTO> buildAll(List<ParticipantesAtendimentos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
