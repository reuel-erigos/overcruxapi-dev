package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetFuncoesCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Funcoes;
import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class FuncoesInstituicaoTOBuilder {

	@Autowired private FuncoesTOBuilder funcoesTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetFuncoesCmd getFuncoesCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public FuncoesInstituicao build(Instituicao instituicao, FuncoesInstituicaoTO to) {
		FuncoesInstituicao entity = new FuncoesInstituicao();

		BeanUtils.copyProperties(to, entity);
		entity.setInstituicao(instituicao);

		if (Objects.nonNull(to.getFuncionario()) && Objects.nonNull(to.getFuncionario()
				.getId())) {
			Funcionario funcionario = getFuncionarioCmd.getById(to.getFuncionario()
					.getId());
			entity.setFuncionario(funcionario);
		}

		if (Objects.nonNull(to.getFuncoes()) && Objects.nonNull(to.getFuncoes()
				.getId())) {
			Funcoes funcoes = getFuncoesCmd.getById(to.getFuncoes()
					.getId());
			entity.setFuncao(funcoes);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

	public FuncoesInstituicaoTO buildTO(FuncoesInstituicao entity) {
		FuncoesInstituicaoTO to = new FuncoesInstituicaoTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
//
//		to.setFuncionario(funcionarioTOBuilder.buildTO(entity.getFuncionario()));
//		to.setFuncoes(funcoesTOBuilder.buildTO(entity.getFuncao()));

		return to;
	}

	public List<FuncoesInstituicaoTO> buildAll(List<FuncoesInstituicao> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

}
