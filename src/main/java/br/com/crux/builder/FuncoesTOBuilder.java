package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
	@Autowired private InstituicaoTOBuilder instituicaoBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public Funcoes build(FuncoesTO to) {
		Funcoes entity = new Funcoes();

		BeanUtils.copyProperties(to, entity);

		Optional.ofNullable(to.getUnidade())
				.ifPresent(u -> {
					Unidade unidade = getUnidadeCmd.getById(u.getIdUnidade());
					entity.setUnidade(unidade);
				});

		Optional.ofNullable(to.getInstituicao())
				.ifPresent(u -> {
					Instituicao instituicao = getInstituicaoCmd.getById(u.getId());
					entity.setInstituicao(instituicao);
				});

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

		to.setUnidade(unidadeBuilder.buildTO(entity.getUnidade()));
		to.setInstituicao(instituicaoBuilder.buildTO(entity.getInstituicao()));

		return to;
	}

	public List<FuncoesTO> buildAll(List<Funcoes> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

}
