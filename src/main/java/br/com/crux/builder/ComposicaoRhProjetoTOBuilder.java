package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ComposicaoRhProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ComposicaoRhProjetoTO;

@Component
public class ComposicaoRhProjetoTOBuilder {

	@Autowired
	GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	GetEmpresaCmd empresaCmd;
	@Autowired
	EmpresaTOBuilder empresaTOBuilder;
	@Autowired
	ProjetoTOBuilder projetoTOBuilder;
	@Autowired
	CargosTOBuilder cargosTOBuilder;
	@Autowired
	TiposContratacoesTOBuilder tiposContratacoesTOBuilder;

	public ComposicaoRhProjeto build(Projeto projeto, ComposicaoRhProjetoTO to) {

		ComposicaoRhProjeto composicaoRhProjeto = new ComposicaoRhProjeto();

		composicaoRhProjeto.setId(to.getId());

		Optional.ofNullable(to.getCargo()).ifPresent(cargo -> {
			composicaoRhProjeto.setCargo(cargosTOBuilder.build(cargo));
		});

		composicaoRhProjeto.setProjeto(projeto);

		composicaoRhProjeto.setQtd(to.getQtd());

		Optional.ofNullable(to.getTiposContratacoes()).ifPresent(tc -> {
			composicaoRhProjeto.setTiposContratacoes(tiposContratacoesTOBuilder.build(to.getTiposContratacoes()));

		});
		composicaoRhProjeto.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return composicaoRhProjeto;
	}

	public ComposicaoRhProjetoTO buildTO(ComposicaoRhProjeto composicaoRhProjeto) {

		ComposicaoRhProjetoTO to = new ComposicaoRhProjetoTO();

		to.setId(composicaoRhProjeto.getId());
		to.setQtd(composicaoRhProjeto.getQtd());

		Optional.ofNullable(composicaoRhProjeto.getCargo()).ifPresent(cargo -> {
			to.setCargo(cargosTOBuilder.buildTO(cargo));
		});

		Optional.ofNullable(composicaoRhProjeto.getTiposContratacoes()).ifPresent(tc -> {
			to.setTiposContratacoes(tiposContratacoesTOBuilder.buildTO(tc));

		});

		return to;
	}

	public List<ComposicaoRhProjetoTO> buildAll(List<ComposicaoRhProjeto> lista) {

		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

}
