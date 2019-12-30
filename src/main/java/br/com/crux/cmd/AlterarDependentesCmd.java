package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DependentesTOBuilder;
import br.com.crux.dao.repository.DependentesRepository;
import br.com.crux.entity.Dependentes;
import br.com.crux.rule.CamposObrigatoriosDependentesFuncionarioRule;
import br.com.crux.to.DependentesTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class AlterarDependentesCmd {

	@Autowired private DependentesRepository repository;
	@Autowired private DependentesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosDependentesFuncionarioRule camposObrigatoriosRule;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	private void alterar(DependentesTO dependenteTO, FuncionarioTO funcionarioTO) {
		camposObrigatoriosRule.verificar(dependenteTO);
		dependenteTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		dependenteTO.setIdFuncionario(funcionarioTO.getId());
		
		Dependentes entity = toBuilder.build(dependenteTO);
		entity.setPessoaFisica(alterarPessoaFisicaCmd.alterar(dependenteTO.getPessoaFisica()));
		
		repository.save(entity);
	}
	
	
	public void alterarAll(List<DependentesTO> dependentesTO, FuncionarioTO funcionarioTO) {
		//Lista de dependentes
		List<Dependentes> dependentesDoFuncionario = repository.findAllByFuncionario(funcionarioTO.getId()).orElse(new ArrayList<Dependentes>());
		
		BiPredicate<DependentesTO, List<DependentesTO>> contemNaLista  = (parte, lista) -> lista.stream().anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									               && 
																									               registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		dependentesDoFuncionario.removeIf(registro -> {
														if(!contemNaLista.test(toBuilder.buildTO(registro), dependentesTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<DependentesTO> novos = dependentesTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoDependente -> alterar(novoDependente, funcionarioTO));
		}

		//Atualiza os registros 
		dependentesTO.stream()
		              .filter(registro -> Objects.nonNull(registro.getId())) 
		              .forEach( registro -> {
												if(contemNaLista.test(registro, toBuilder.buildAll(dependentesDoFuncionario))){
													alterar(registro, funcionarioTO);
												}
		});
	}
}
