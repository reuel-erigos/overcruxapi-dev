package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FrequenciasAlunosTOBuilder;
import br.com.crux.dao.repository.FrequenciasAlunosRepository;
import br.com.crux.entity.FrequenciasAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFrequenciasAlunosRule;
import br.com.crux.to.FrequenciasAlunosTO;

@Component
public class AlterarFrequenciasAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private FrequenciasAlunosRepository repository;
	@Autowired private CamposObrigatoriosFrequenciasAlunosRule camposObrigatoriosRule;
	@Autowired private FrequenciasAlunosTOBuilder frequenciasAlunosTOBuilder;
	@Autowired private GetFrequenciasAlunosCmd frequenciasAlunosCmd;
	
	
	private void salvar(FrequenciasAlunosTO to) {
		camposObrigatoriosRule.verificar(to);
		
		FrequenciasAlunos entity = null;
		if(Objects.nonNull(to.getId())) {
			entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Frequencia do aluno informada não existe.") );
		}
		
		entity = frequenciasAlunosTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		repository.save(entity);
	}	
	
	public void alterarAll(List<FrequenciasAlunosTO> listaTO, Long idAtividade, Long dataFrequenciaLong) {
		//Lista de frequencia de alunos.
		List<FrequenciasAlunos> listaBaseDados = frequenciasAlunosCmd.getAll(idAtividade, dataFrequenciaLong);
		
		BiPredicate<FrequenciasAlunosTO, List<FrequenciasAlunosTO>> contemNaLista  = (parte, lista) -> lista.stream()
																						                    .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																						              		                 && 
																						              		                 registroTO.getId().equals(parte.getId()));
		
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		listaBaseDados.removeIf(registro -> {
														if(!contemNaLista.test(frequenciasAlunosTOBuilder.buildTO(registro), listaTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<FrequenciasAlunosTO> novos = listaTO.stream()
				                                 .filter(registro -> Objects.isNull(registro.getId()))
				                                 .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoResponsavel -> salvar(novoResponsavel));
		}

		//Atualiza os registros 
		listaTO.stream()
		       .filter(registro -> Objects.nonNull(registro.getId()))
		       .forEach( registro -> {
										if(contemNaLista.test(registro, frequenciasAlunosTOBuilder.buildAll(listaBaseDados))){
											salvar(registro);
										}
		});
	}
	
}
