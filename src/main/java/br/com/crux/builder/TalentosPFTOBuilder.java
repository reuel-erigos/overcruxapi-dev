package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetQuestionariosCmd;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Questionario;
import br.com.crux.entity.TalentosPf;
import br.com.crux.to.TalentosPfTO;

@Component
public class TalentosPFTOBuilder {

	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private QuestionariosTOBuilder questionariosTOBuilder;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetQuestionariosCmd getQuestionariosCmd;

	public TalentosPf build(TalentosPfTO p) {
		TalentosPf retorno = new TalentosPf();

		retorno.setId(p.getId());
		retorno.setRespostaTalento(p.getRespostaTalento());
		retorno.setDataRespostaTalento(p.getDataRespostaTalento());
		retorno.setNrNotaCompetencia(p.getNrNotaCompetencia());
		retorno.setObservacao(p.getObservacao());

		Optional.ofNullable(p.getFuncionario()).ifPresent(f -> {
			if (Objects.nonNull(f.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(f.getId());
				retorno.setPessoasFisica(funcionario.getPessoasFisica());
			}
		});

		Optional.ofNullable(p.getQuestionario()).ifPresent(q -> {
			if (Objects.nonNull(q.getId())) {
				Questionario questionario = getQuestionariosCmd.getById(q.getId());
				retorno.setQuestionario(questionario);
			}
		});

		retorno.setUsuariosAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public TalentosPfTO buildTO(TalentosPf p) {
		TalentosPfTO retorno = new TalentosPfTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setRespostaTalento(p.getRespostaTalento());
		retorno.setDataRespostaTalento(p.getDataRespostaTalento());
		retorno.setNrNotaCompetencia(p.getNrNotaCompetencia());
		retorno.setObservacao(p.getObservacao());
		
		Funcionario funcionario = getFuncionarioCmd.getPorPessoa(p.getPessoasFisica().getId());
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(funcionario));

		retorno.setQuestionario(questionariosTOBuilder.buildTO(p.getQuestionario()));
		retorno.setUsuarioAlteracao(p.getUsuariosAlteracao());

		return retorno;
	}

	public List<TalentosPfTO> buildAll(List<TalentosPf> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
