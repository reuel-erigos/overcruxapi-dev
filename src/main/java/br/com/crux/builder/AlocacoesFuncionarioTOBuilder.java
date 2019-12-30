package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.entity.AlocacoesFuncionario;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.AlocacoesFuncionarioTO;

@Component
public class AlocacoesFuncionarioTOBuilder {

	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;

	public AlocacoesFuncionario build(AlocacoesFuncionarioTO to) {

		AlocacoesFuncionario retorno = new AlocacoesFuncionario();

		retorno.setId(to.getId());
		retorno.setDataInicioVinculacao(to.getDataInicioVinculacao());
		retorno.setDataFimVinculacao(to.getDataFimVinculacao());
		retorno.setFuncionario(funcionarioTOBuilder.buildSemRelacionamentosCircular(to.getFuncionario()));
		
		Optional.ofNullable(to.getPrograma()).ifPresent(pro -> {
			if(Objects.nonNull(pro.getId())) {
				Programa programa = getProgramaCmd.getById(pro.getId());
				retorno.setPrograma(programa);
			}
		});
		
		Optional.ofNullable(to.getProjeto()).ifPresent(pro -> {
			if(Objects.nonNull(pro.getId())) {
				Projeto projeto = getProjetoCmd.getById(pro.getId());
				retorno.setProjeto(projeto);
			}
		});

		retorno.setValorAlocacao(to.getValorAlocacao());
		retorno.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return retorno;
	}

	public AlocacoesFuncionarioTO buildTO(AlocacoesFuncionario p) {
		AlocacoesFuncionarioTO retorno = new AlocacoesFuncionarioTO();

		if (Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDataInicioVinculacao(p.getDataInicioVinculacao());
		retorno.setDataFimVinculacao(p.getDataFimVinculacao());
		retorno.setFuncionario(funcionarioTOBuilder.buildTOSemRelacionamentosCircular(p.getFuncionario()));
		
		Optional.ofNullable(p.getPrograma()).ifPresent(pro -> {
			if(Objects.nonNull(pro.getId())) {
				retorno.setPrograma(programaTOBuilder.buildTO(pro));
			}
		});
		
		Optional.ofNullable(p.getProjeto()).ifPresent(pro -> {
			if(Objects.nonNull(pro.getId())) {
				retorno.setProjeto(projetoTOBuilder.buildTO(pro));
			}
		});
		
		retorno.setValorAlocacao(p.getValorAlocacao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());		

		return retorno;
	}

	public List<AlocacoesFuncionarioTO> buildAll(List<AlocacoesFuncionario> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<AlocacoesFuncionario> buildTOAll(List<AlocacoesFuncionarioTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}


}
