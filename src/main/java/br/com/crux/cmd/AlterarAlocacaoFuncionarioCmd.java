package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlocacoesFuncionarioTOBuilder;
import br.com.crux.dao.repository.AlocacaoFuncionarioRepository;
import br.com.crux.entity.AlocacoesFuncionario;
import br.com.crux.rule.CamposObrigatoriosAlocacoesFuncionarioRule;
import br.com.crux.to.AlocacoesFuncionarioTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class AlterarAlocacaoFuncionarioCmd {

	@Autowired private AlocacaoFuncionarioRepository repository;
	@Autowired private AlocacoesFuncionarioTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosAlocacoesFuncionarioRule camposObrigatoriosRule;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	private void alterar(AlocacoesFuncionarioTO alocacaoFuncionarioTO, FuncionarioTO funcionarioTO) {
		camposObrigatoriosRule.verificar(alocacaoFuncionarioTO);
		alocacaoFuncionarioTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		alocacaoFuncionarioTO.setFuncionario(funcionarioTO);
		AlocacoesFuncionario entity = toBuilder.build(alocacaoFuncionarioTO);
		repository.save(entity);
	}
	
	
	public void alterarAll(List<AlocacoesFuncionarioTO> alocacoesTO, FuncionarioTO funcionarioTO) {
		//Lista de dependentes
		List<AlocacoesFuncionario> alocacoesFuncionario = repository.findAllByFuncionario(funcionarioTO.getId()).orElse(new ArrayList<AlocacoesFuncionario>());
		
		BiPredicate<AlocacoesFuncionarioTO, List<AlocacoesFuncionarioTO>> contemNaLista  = (parte, lista) -> lista.stream()
				                                                                                                  .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                        && 
																									                        registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		alocacoesFuncionario.removeIf(registro -> {
														if(!contemNaLista.test(toBuilder.buildTO(registro), alocacoesTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<AlocacoesFuncionarioTO> novos = alocacoesTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoRegistro -> alterar(novoRegistro, funcionarioTO));
		}

		//Atualiza os registros 
		alocacoesTO.stream()
		              .filter(registro -> Objects.nonNull(registro.getId())) 
		              .forEach( registro -> {
												if(contemNaLista.test(registro, toBuilder.buildAll(alocacoesFuncionario))){
													alterar(registro, funcionarioTO);
												}
		});
	}
}
