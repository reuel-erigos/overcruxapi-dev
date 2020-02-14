package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Funcoes;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.to.FuncoesTO;

@Component
public class FuncoesTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public Funcoes build(FuncoesTO to) {
		Funcoes entity = new Funcoes();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade()
				.getIdUnidade())) {
			Unidade unidade = getUnidadeCmd.getById(to.getUnidade()
					.getIdUnidade());
			entity.setUnidade(unidade);
			Instituicao instituicao = getInstituicaoCmd.getPorUnidade(unidade);
			entity.setInstituicao(instituicao);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

	public FuncoesTO buildTO(Funcoes entity) {
		FuncoesTO to = new FuncoesTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);

		to.setUnidade(unidadeBuilder.buildTOPraCombo(entity.getUnidade()));

		return to;
	}

	public List<FuncoesTO> buildAll(List<Funcoes> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}
	
	public FuncoesTO buildTOEnxuto(Funcoes entity) {
		FuncoesTO to = new FuncoesTO();

		if (Objects.isNull(entity)) {
			return to;
		}


		to.setId(entity.getId());
		to.setNome(entity.getNome());
		
		return to;
	}


}
