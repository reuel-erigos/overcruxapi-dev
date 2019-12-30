package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.Dependentes;
import br.com.crux.entity.Funcionario;
import br.com.crux.to.DependentesTO;

@Component
public class DependentesTOBuilder {

	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	
	
	public Dependentes build(DependentesTO to) {

		Dependentes retorno = new Dependentes();
		
		retorno.setId(to.getId());
		
		Funcionario funcionario = getFuncionarioCmd.getById(to.getIdFuncionario());
		retorno.setFuncionario(funcionario);
		
		retorno.setPessoaFisica(pessoaFisicaTOBuilder.build(to.getPessoaFisica()));
		
		retorno.setDescricaoGrauParentesco(to.getDescricaoGrauParentesco());
		retorno.setDescricaoOutrasInformacoes(to.getDescricaoOutrasInformacoes());
		retorno.setDescricaoDesligamento(to.getDescricaoDesligamento());
		retorno.setDataDesligamento(to.getDataDesligamento());
		retorno.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return retorno;
	}

	public DependentesTO buildTO(Dependentes p) {
		DependentesTO retorno = new DependentesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		
		if(Objects.nonNull(p.getFuncionario())) {
			retorno.setIdFuncionario(p.getFuncionario().getId());
		}
		retorno.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		retorno.setDescricaoGrauParentesco(p.getDescricaoGrauParentesco());
		retorno.setDescricaoOutrasInformacoes(p.getDescricaoOutrasInformacoes());
		retorno.setDescricaoDesligamento(p.getDescricaoDesligamento());
		retorno.setDataDesligamento(p.getDataDesligamento());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());		

		return retorno;
	}

	public List<DependentesTO> buildAll(List<Dependentes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<Dependentes> buildTOAll(List<DependentesTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}


}
