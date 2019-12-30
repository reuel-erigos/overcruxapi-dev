package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresTurmaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresTurmaRepository;
import br.com.crux.entity.ColaboradoresTurma;
import br.com.crux.rule.CamposObrigatoriosColaboradoresTurmaRule;
import br.com.crux.to.ColaboradoresTurmaTO;
import br.com.crux.to.TurmasTO;

@Component
public class AlterarColaboradoresTurmaCmd {

	@Autowired private ColaboradoresTurmaRepository repository;
	@Autowired private CamposObrigatoriosColaboradoresTurmaRule camposObrigatoriosRule;
	@Autowired private ColaboradoresTurmaTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;


	
	private void alterar(ColaboradoresTurmaTO colaboradorTO, TurmasTO turmaTO) {
		camposObrigatoriosRule.verificar(colaboradorTO);
		colaboradorTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		colaboradorTO.setIdTurma(turmaTO.getId());
		ColaboradoresTurma entity = toBuilder.build(colaboradorTO);
		
		repository.save(entity);
	}
	
	
	public void alterarAll(List<ColaboradoresTurmaTO> colaboradorTO, TurmasTO turmaTO) {
		//Lista de ColaboradoresTurma
		List<ColaboradoresTurma> colaboradoresTurma = repository.findByIdTurma(turmaTO.getId()).orElse(new ArrayList<ColaboradoresTurma>());
		
		BiPredicate<ColaboradoresTurmaTO, List<ColaboradoresTurmaTO>> contemNaLista  = (parte, lista) -> lista.stream().anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                             && 
																									                             registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		colaboradoresTurma.removeIf(registro -> {
														if(!contemNaLista.test(toBuilder.buildTO(registro), colaboradorTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<ColaboradoresTurmaTO> novos = colaboradorTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> alterar(novo, turmaTO));
		}

		//Atualiza os registros 
		colaboradorTO.stream()
		              .filter(registro -> Objects.nonNull(registro.getId())) 
		              .forEach( registro -> {
												if(contemNaLista.test(registro, toBuilder.buildAll(colaboradoresTurma))){
													alterar(registro, turmaTO);
												}
		});
	}



}
