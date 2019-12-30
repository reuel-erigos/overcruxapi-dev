package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.ColaboradoresTurma;
import br.com.crux.entity.Funcionario;
import br.com.crux.to.ColaboradoresTurmaTO;

@Component
public class ColaboradoresTurmaTOBuilder {

	@Autowired private CargosTOBuilder cargosTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetCargosCmd getCargosCmd;

	public ColaboradoresTurma build(ColaboradoresTurmaTO param) {
		ColaboradoresTurma retorno = new ColaboradoresTurma();
				
		retorno.setId(param.getId());
		retorno.setDataEntradaTurma(param.getDataEntradaTurma());
		retorno.setDataSaidaTurma(param.getDataSaidaTurma());
		
		Optional.ofNullable(param.getCargo()).ifPresent(p -> {
			Cargo cargo = getCargosCmd.getById(p.getId());
			retorno.setCargo(cargo);
		});
		
		Optional.ofNullable(param.getFuncionario()).ifPresent(p -> {
			Funcionario funcionario = getFuncionarioCmd.getById(p.getId());
			retorno.setFuncionario(funcionario);
		});
		
		retorno.setIdTurma(param.getIdTurma());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
				
		return retorno;
	}

	
	public ColaboradoresTurmaTO buildTO(ColaboradoresTurma param) {
		ColaboradoresTurmaTO retorno = new ColaboradoresTurmaTO();
		

		if(Objects.isNull(param)) {
			return retorno;
		}
		
		retorno.setId(param.getId());
		retorno.setDataEntradaTurma(param.getDataEntradaTurma());
		retorno.setDataSaidaTurma(param.getDataSaidaTurma());
		
		Optional.ofNullable(param.getCargo()).ifPresent(p -> {
			retorno.setCargo(cargosTOBuilder.buildTO(p));
		});
		
		Optional.ofNullable(param.getFuncionario()).ifPresent(p -> {
			retorno.setFuncionario(funcionarioTOBuilder.buildTO(p));
		});
		
		retorno.setIdTurma(param.getIdTurma());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		return retorno;
	}
	
	
	public List<ColaboradoresTurmaTO> buildAll(List<ColaboradoresTurma> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
