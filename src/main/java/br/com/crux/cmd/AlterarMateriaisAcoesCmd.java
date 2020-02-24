package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisAcoesTOBuilder;
import br.com.crux.dao.repository.MateriaisAcoesRepository;
import br.com.crux.entity.MateriaisAcoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMateriaisAcoesRule;
import br.com.crux.to.MateriaisAcoesTO;

@Component
public class AlterarMateriaisAcoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private MateriaisAcoesRepository repository;
	@Autowired private CamposObrigatoriosMateriaisAcoesRule camposObrigatoriosRule;
	@Autowired private MateriaisAcoesTOBuilder toBuilder;
	

	private void alterar(MateriaisAcoesTO to) {
		camposObrigatoriosRule.verificar(to);
		MateriaisAcoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Material da ação informada não existe."));
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity = toBuilder.build(to);
		repository.save(entity);
	}
	
	
	
	public void alterarAll(List<MateriaisAcoesTO> materiaisAcoesTO, Long idAcao) {
		
		//Lista os registros no banco de dados
		List<MateriaisAcoes> acoes = repository.findAllByIdAcao(idAcao).orElse(new ArrayList<MateriaisAcoes>());
		
		
		BiPredicate<MateriaisAcoesTO, List<MateriaisAcoesTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                      .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                		                        && 
                                                                                		                        registroTO.getId().equals(parte.getId()));

		
		//Remove da lista todos os registros que não contém no Banco de Dados
		acoes.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), materiaisAcoesTO)){
											repository.delete(registro); 
											return true;
										}
										return false;
					                }
		                               
				      );
		
		//Adiciona os novos registros
		List<MateriaisAcoesTO> novos = materiaisAcoesTO.stream()
									                    .filter(registro -> Objects.isNull(registro.getId()))
									                    .collect(Collectors.toList());
		
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.setIdAcao(idAcao);
				alterar(novo);
			});
		}

		//Atualiza os registros 
		materiaisAcoesTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, toBuilder.buildAll(acoes))){
				registro.setIdAcao(idAcao);
				alterar(registro);
			}
		});
	}
	
	
	
}
