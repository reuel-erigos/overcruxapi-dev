package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AnexosAcaoPlanejamentoTOBuilder;
import br.com.crux.dao.repository.AnexosAcaoPlanejamentoRepository;
import br.com.crux.entity.AnexosAcaoPlanejamento;
import br.com.crux.to.AnexosAcaoPlanejamentoTO;

@Component
public class AlterarAnexosAcaoPlanejamentoCmd {

	@Autowired private AnexosAcaoPlanejamentoRepository repository;
	@Autowired private AnexosAcaoPlanejamentoTOBuilder toBuilder;
	

	private void alterar(AnexosAcaoPlanejamentoTO to) {
		AnexosAcaoPlanejamento entity = toBuilder.build(to);
		repository.save(entity);
	}
	
	
	
	public void alterarAll(List<AnexosAcaoPlanejamentoTO> listaTO, Long idAcao) {
		
		//Lista os registros no banco de dados
		List<AnexosAcaoPlanejamento> entitys = repository.findAllByIdAcao(idAcao).orElse(new ArrayList<AnexosAcaoPlanejamento>());
		
		
		BiPredicate<AnexosAcaoPlanejamentoTO, List<AnexosAcaoPlanejamentoTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                      .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                		                        && 
                                                                                		                        registroTO.getId().equals(parte.getId()));

		
		//Remove da lista todos os registros que não contém no Banco de Dados
		entitys.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											repository.delete(registro); 
											return true;
										}
										return false;
					                }
		                               
				      );
		
		//Adiciona os novos registros
		List<AnexosAcaoPlanejamentoTO> novos = listaTO.stream()
									                  .filter(registro -> Objects.isNull(registro.getId()))
									                  .collect(Collectors.toList());
		
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.setIdAcao(idAcao);
				alterar(novo);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
		       .filter(registro -> Objects.nonNull(registro.getId()))
		       .forEach( registro -> {
		                	
        	registro.setIdAcao(idAcao);
			if(contemNaLista.test(registro, toBuilder.buildAllTO(entitys))){
				alterar(registro);
			}
		});
	}
	
	
	
}
