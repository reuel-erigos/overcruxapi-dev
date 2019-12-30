package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ComposicaoRhPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ComposicaoRhProgramaTO;

@Component
public class ComposicaoRhProgramaTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetEmpresaCmd empresaCmd;
	@Autowired EmpresaTOBuilder empresaTOBuilder;
	@Autowired ProjetoTOBuilder projetoTOBuilder;
	@Autowired CargosTOBuilder cargosTOBuilder;
	@Autowired TiposContratacoesTOBuilder tiposContratacoesTOBuilder;

	public ComposicaoRhPrograma build(Programa programa, ComposicaoRhProgramaTO composicaoRhProgramaTO) {

		ComposicaoRhPrograma composicaoRhPrograma = new ComposicaoRhPrograma();

		composicaoRhPrograma.setId(composicaoRhProgramaTO.getId());

		Optional.ofNullable(composicaoRhProgramaTO.getCargo()).ifPresent(cargo -> {
			composicaoRhPrograma.setCargo(cargosTOBuilder.build(cargo));
		});

		composicaoRhPrograma.setPrograma(programa);

		composicaoRhPrograma.setQtd(composicaoRhProgramaTO.getQtd());

		Optional.ofNullable(composicaoRhProgramaTO.getTiposContratacoes()).ifPresent(tc -> {
			composicaoRhPrograma.setTiposContratacoes(tiposContratacoesTOBuilder.build(composicaoRhProgramaTO.getTiposContratacoes()));

		});
		composicaoRhPrograma.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return composicaoRhPrograma;
	}

	public ComposicaoRhProgramaTO buildTO(ComposicaoRhPrograma composicaoRhPrograma) {

		ComposicaoRhProgramaTO to = new ComposicaoRhProgramaTO();

		to.setId(composicaoRhPrograma.getId());
		to.setQtd(composicaoRhPrograma.getQtd());

		Optional.ofNullable(composicaoRhPrograma.getCargo()).ifPresent(cargo -> {
			to.setCargo(cargosTOBuilder.buildTO(cargo));
		});

		Optional.ofNullable(composicaoRhPrograma.getTiposContratacoes()).ifPresent(tc -> {
			to.setTiposContratacoes(tiposContratacoesTOBuilder.buildTO(tc));

		});

		return to;
	}

	public List<ComposicaoRhProgramaTO> buildAll(List<ComposicaoRhPrograma> lista) {

		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

}
