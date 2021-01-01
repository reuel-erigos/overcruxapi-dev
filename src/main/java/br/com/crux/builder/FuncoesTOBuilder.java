package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Funcoes;
import br.com.crux.to.FuncoesTO;

@Component
public class FuncoesTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetCargosCmd getCargosCmd;
	@Autowired private CargosTOBuilder cargosTOBuilder;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	

	public Funcoes build(FuncoesTO to) {
		Funcoes entity = new Funcoes();

		BeanUtils.copyProperties(to, entity);

		getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		if(to.getCargo() != null && to.getCargo().getId() != null) {
			entity.setCargo(getCargosCmd.getById(to.getCargo().getId()));
		}
		
		entity.setUnidade(getUnidadeCmd.getById(getUnidadeLogadaCmd.getUnidadeTO().getIdUnidade()));
		entity.setInstituicao(getInstituicaoCmd.getById(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId()));
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

		to.setCargo(cargosTOBuilder.buildTO(entity.getCargo()));

		return to;
	}

	public List<FuncoesTO> buildAll(List<Funcoes> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}
	
	public FuncoesTO buildTOCombo(Funcoes entity) {
		FuncoesTO to = new FuncoesTO();

		if (Objects.isNull(entity)) {
			return to;
		}


		to.setId(entity.getId());
		to.setNome(entity.getNome());
		
		return to;
	}


}
