package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisAtividadeTOBuilder;
import br.com.crux.dao.repository.MateriaisAtividadeRepository;
import br.com.crux.entity.MateriaisAtividade;
import br.com.crux.rule.CamposObrigatoriosMateriaisAtividadeRule;
import br.com.crux.to.MateriaisAtividadeTO;

@Component
public class AlterarMateriaisAtividadeCmd {

	@Autowired private MateriaisAtividadeRepository repository;
	@Autowired private GetMateriaisAtividadeCmd getMateriaisAtividadeCmd;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private MateriaisAtividadeTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMateriaisAtividadeRule camposObrigatoriosRule;

	public void alterar(MateriaisAtividadeTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		MateriaisAtividade entity = toBuilder.build(to);

		repository.save(entity);

	}
	
	
	public void alterarAll(List<MateriaisAtividadeTO> materiaisAtividadeTO, Long idAtividade) {
		
		//Lista de colaboradores da atividade.
		List<MateriaisAtividade> materiaisAtividade = getMateriaisAtividadeCmd.getAllPorAtividade(idAtividade);
		
		BiPredicate<MateriaisAtividadeTO, List<MateriaisAtividadeTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                      .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                                    		                 && 
                                                                                                    		                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		materiaisAtividade.removeIf(registro -> {
														if(!contemNaLista.test(toBuilder.buildTO(registro), materiaisAtividadeTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<MateriaisAtividadeTO> novos = materiaisAtividadeTO.stream()
				                                         .filter(registro -> Objects.isNull(registro.getId()))
				                                         .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.setIdAtividade(idAtividade);
				alterar(novo);
			});
		}

		//Atualiza os registros 
		materiaisAtividadeTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, toBuilder.buildAll(materiaisAtividade))){
				registro.setIdAtividade(idAtividade);
				alterar(registro);
			}
		});
	}
	
}
