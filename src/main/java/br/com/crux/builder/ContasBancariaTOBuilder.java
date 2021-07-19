package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.entity.Unidade;
import br.com.crux.to.BancoTO;
import br.com.crux.to.ContasBancariaTO;

@Component
public class ContasBancariaTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	
	public ContasBancaria build(ContasBancariaTO to) {
		ContasBancaria entity = new ContasBancaria();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getBanco())) {
			entity.setNumeroBanco(to.getBanco().getNumero());
			entity.setNomeBanco(to.getBanco().getNome());
		}

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade().getIdUnidade())) {
			Unidade unidade = getUnidadeCmd.getById(to.getUnidade().getIdUnidade());
			entity.setUnidade(unidade);
		}

		if (Objects.nonNull(to.getCategoriasContabeis()) && Objects.nonNull(to.getCategoriasContabeis().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(to.getCategoriasContabeis().getId());
			entity.setCategoriasContabeis(categoria);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

	public ContasBancariaTO buildTO(ContasBancaria entity) {
		ContasBancariaTO to = new ContasBancariaTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
		to.setUnidade(unidadeBuilder.buildTO(entity.getUnidade()));
		to.setBanco(new BancoTO(entity.getNomeBanco(), entity.getNumeroBanco()));
		
		if(Objects.nonNull(entity.getCategoriasContabeis())) {
			to.setCategoriasContabeis(categoriasContabeisTOBuilder.buildTO(entity.getCategoriasContabeis()));
		}

		return to;
	}

	public List<ContasBancariaTO> buildAll(List<ContasBancaria> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public ContasBancariaTO buildTOCombo(ContasBancaria entity) {
		ContasBancariaTO to = new ContasBancariaTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
		to.setBanco(new BancoTO(entity.getNomeBanco(), entity.getNumeroBanco()));

		return to;

	}

	public List<ContasBancariaTO> buildAllCombo(List<ContasBancaria> list) {
		return list.stream()
				.map(dto -> buildTOCombo(dto))
				.collect(Collectors.toList());
	}

}
