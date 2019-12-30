package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetTiposContratacoesCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.TiposContratacoes;
import br.com.crux.to.ColaboradoresProjetoTO;

@Component
public class ColaboradoresProjetoTOBuilder {

	@Autowired private CargosTOBuilder cargoTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private TiposContratacoesTOBuilder tiposContratacoesTOBuilder;
	@Autowired private GetCargosCmd getCargosCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetTiposContratacoesCmd getTiposContratacoesCmd;

	public ColaboradoresProjeto build(Projeto projeto, ColaboradoresProjetoTO p) {
		ColaboradoresProjeto retorno = new ColaboradoresProjeto();

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setProjeto(projeto);

		Optional.ofNullable(p.getCargo()).ifPresent(pro -> {
			Cargo cargo = getCargosCmd.getById(pro.getId());
			retorno.setCargo(cargo);
		});

		Optional.ofNullable(p.getFuncionario()).ifPresent(pro -> {
			Funcionario funcionario = getFuncionarioCmd.getById(pro.getId());
			retorno.setFuncionario(funcionario);
		});

		Optional.ofNullable(p.getTiposContratacoes()).ifPresent(tp -> {
			TiposContratacoes tiposContratacoes = getTiposContratacoesCmd.getById(tp.getId());
			retorno.setTiposContratacoes(tiposContratacoes);
		});
		
		
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;
	}

	public ColaboradoresProjetoTO buildTO(ColaboradoresProjeto p) {
		ColaboradoresProjetoTO retorno = new ColaboradoresProjetoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));
		
		retorno.setTiposContratacoes(tiposContratacoesTOBuilder.buildTO(p.getTiposContratacoes()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<ColaboradoresProjetoTO> buildAll(List<ColaboradoresProjeto> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public ColaboradoresProjetoTO buildTOParaLista(ColaboradoresProjeto p) {
		ColaboradoresProjetoTO retorno = new ColaboradoresProjetoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		retorno.setCargo(cargoTOBuilder.buildTO(p.getCargo()));
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));
		
		retorno.setTiposContratacoes(tiposContratacoesTOBuilder.buildTO(p.getTiposContratacoes()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<ColaboradoresProjetoTO> buildAllParaLista(List<ColaboradoresProjeto> dtos) {
		return dtos.stream().map(dto -> buildTOParaLista(dto)).collect(Collectors.toList());
	}

}
