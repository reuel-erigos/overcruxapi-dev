package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.FaltasFuncionario;
import br.com.crux.entity.Funcionario;
import br.com.crux.to.FaltasFuncionarioTO;

@Component
public class FaltasFuncionarioTOBuilder {

	@Autowired private FuncionarioTOBuilder funcionarioBuilder;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;

	public FaltasFuncionario build(FaltasFuncionarioTO p) {
		FaltasFuncionario retorno = new FaltasFuncionario();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setJutificativaFalta(p.getJutificativaFalta());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataFaltaFuncionario(p.getDataFaltaFuncionario());

		Optional.ofNullable(p.getFuncionarioFaltou()).ifPresent(ff -> {
			if (Objects.nonNull(ff.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(ff.getId());
				retorno.setFuncionarioFaltou(funcionario);
			}
		});

		Optional.ofNullable(p.getFuncionarioCadastrouFalta()).ifPresent(ff -> {
			if (Objects.nonNull(ff.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(ff.getId());
				retorno.setFuncionarioCadastrouFalta(funcionario);
			}
		});
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public FaltasFuncionarioTO buildTO(FaltasFuncionario p) {
		FaltasFuncionarioTO retorno = new FaltasFuncionarioTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setJutificativaFalta(p.getJutificativaFalta());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataFaltaFuncionario(p.getDataFaltaFuncionario());
		retorno.setFuncionarioFaltou(funcionarioBuilder.buildTO(p.getFuncionarioFaltou()));
		retorno.setFuncionarioCadastrouFalta(funcionarioBuilder.buildTO(p.getFuncionarioCadastrouFalta()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<FaltasFuncionarioTO> buildAll(List<FaltasFuncionario> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
