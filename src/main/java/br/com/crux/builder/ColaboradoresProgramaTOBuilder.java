package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Programa;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class ColaboradoresProgramaTOBuilder {

	@Autowired private CargosTOBuilder cargoTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetCargosCmd getCargosCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public ColaboradoresPrograma build(Programa programa, ColaboradoresProgramaTO p) {
		ColaboradoresPrograma retorno = new ColaboradoresPrograma();

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setPrograma(programa);

		Optional.ofNullable(p.getCargo()).ifPresent(pro -> {
			Cargo cargo = getCargosCmd.getById(pro.getId());
			retorno.setCargo(cargo);
		});

		Optional.ofNullable(p.getFuncionario()).ifPresent(pro -> {
			Funcionario funcionario = getFuncionarioCmd.getById(pro.getId());
			retorno.setFuncionario(funcionario);
		});

		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;
	}

	public ColaboradoresProgramaTO buildTO(ColaboradoresPrograma p) {
		ColaboradoresProgramaTO retorno = new ColaboradoresProgramaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<ColaboradoresProgramaTO> buildAll(List<ColaboradoresPrograma> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public ColaboradoresProgramaTO buildTOParaLista(ColaboradoresPrograma p) {
		ColaboradoresProgramaTO retorno = new ColaboradoresProgramaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<ColaboradoresProgramaTO> buildAllParaLista(List<ColaboradoresPrograma> dtos) {
		return dtos.stream().map(dto -> buildTOParaLista(dto)).collect(Collectors.toList());
	}

}
