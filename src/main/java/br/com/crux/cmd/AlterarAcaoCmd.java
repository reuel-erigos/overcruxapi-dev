package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAcaoRule;
import br.com.crux.to.AcaoTO;

@Component
public class AlterarAcaoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AcaoRepository repository;
	@Autowired private CamposObrigatoriosAcaoRule camposObrigatoriosRule;
	@Autowired private AcaoTOBuilder toBuilder;
	

	private void alterar(AcaoTO to) {
		camposObrigatoriosRule.verificar(to);

		Acoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Ação informada não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = toBuilder.build(to);

		repository.save(entity);

	}
	
	
	
	public void alterarAll(List<AcaoTO> acoesTO, Long idOficina) {
		
		//Lista de AcaoTO da oficina.
		List<Acoes> acoes = repository.findAllByOficina(idOficina).orElse(new ArrayList<Acoes>());
		
		
		BiPredicate<AcaoTO, List<AcaoTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                  .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                		                 && 
                                                                                		                 registroTO.getId().equals(parte.getId()));

		
		//Remove da lista todos os registros que não contém no Banco de Dados
		acoes.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), acoesTO)){
											repository.delete(registro); 
											return true;
										}
										return false;
					                }
		                               
				      );
		
		//Adiciona os novos registros
		List<AcaoTO> novos = acoesTO.stream()
				                    .filter(registro -> Objects.isNull(registro.getId()))
				                    .collect(Collectors.toList());
		
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.getOficina().setId(idOficina);
				alterar(novo);
			});
		}

		//Atualiza os registros 
		acoesTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, toBuilder.buildAll(acoes))){
				registro.getOficina().setId(idOficina);
				alterar(registro);
			}
		});
	}
	
	
	
}
