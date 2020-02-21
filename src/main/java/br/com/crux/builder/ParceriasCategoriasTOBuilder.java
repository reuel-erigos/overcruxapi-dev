package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class ParceriasCategoriasTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired private ParceriasProgramaTOBuilder parceriasProgramaTOBuilder;
	@Autowired private ParceriasProjetoTOBuilder parceriasProjetoTOBuilder;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;

	public ParceriasCategorias build(ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto, ParceriasCategoriasTO to) {

		ParceriasCategorias e = new ParceriasCategorias();

		BeanUtils.copyProperties(to, e);

		to.setCategoriasContabeis(categoriasContabeisTOBuilder.buildTOCombo(e.getCategoriasContabeis()));

		if (Objects.nonNull(to.getCategoriasContabeis()) && Objects.nonNull(to.getCategoriasContabeis()
				.getId())) {
			CategoriasContabeis entity = getCategoriasContabeisCmd.getById(to.getCategoriasContabeis()
					.getId());
			e.setCategoriasContabeis(entity);
		}

		if (Objects.nonNull(parceriasPrograma)) {
			e.setParceriasPrograma(parceriasPrograma);
		}

		if (Objects.nonNull(parceriasProjeto)) {
			e.setParceriasProjeto(parceriasProjeto);
		}

		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return e;
	}

	public ParceriasCategoriasTO buildTO(ParceriasCategorias e) {

		ParceriasCategoriasTO to = new ParceriasCategoriasTO();

		BeanUtils.copyProperties(e, to);

		to.setCategoriasContabeis(categoriasContabeisTOBuilder.buildTOCombo(e.getCategoriasContabeis()));

		if (Objects.nonNull(e.getParceriasPrograma())) {
			to.setParceriasPrograma(parceriasProgramaTOBuilder.buildTOCombo(e.getParceriasPrograma()));
		}

		if (Objects.nonNull(e.getParceriasPrograma())) {
			to.setParceriasProjeto(parceriasProjetoTOBuilder.buildTOCombo(e.getParceriasProjeto()));
		}

		return to;
	}

	public List<ParceriasCategoriasTO> buildAll(List<ParceriasCategorias> lista) {

		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}
