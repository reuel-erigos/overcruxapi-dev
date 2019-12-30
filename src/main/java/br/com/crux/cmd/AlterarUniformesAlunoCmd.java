package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UniformesAlunoTOBuilder;
import br.com.crux.dao.repository.UniformesAlunoRepository;
import br.com.crux.entity.UniformesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosUniformesAlunoRule;
import br.com.crux.to.UniformesAlunoTO;

@Component
public class AlterarUniformesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private UniformesAlunoRepository repository;
	@Autowired private CamposObrigatoriosUniformesAlunoRule camposObrigatoriosRule;
	@Autowired private UniformesAlunoTOBuilder uniformesAlunoTOBuilder;
	@Autowired private GetUniformesAlunoCmd getUniformesAlunoCmd;
	
	private void salvar(UniformesAlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		
		UniformesAluno entity = null;
		if(Objects.nonNull(to.getId())) {
			entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Uniforme do aluno informado não existe.") );
		}
		entity = uniformesAlunoTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		repository.save(entity);
	}	
	
	public void alterarAll(List<UniformesAlunoTO> vulnerabilidadesTO, Long idAtividade) {
		//Lista de uniformes do aluno.
		List<UniformesAluno> uniformesEntregues = getUniformesAlunoCmd.getAllAlunosMatriculados(idAtividade);
		
		BiPredicate<UniformesAlunoTO, List<UniformesAlunoTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                      .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                                    		                 && 
                                                                                                    		                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		uniformesEntregues.removeIf(registro -> {
														if(!contemNaLista.test(uniformesAlunoTOBuilder.buildTO(registro), vulnerabilidadesTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<UniformesAlunoTO> novos = vulnerabilidadesTO.stream()
				                                         .filter(registro -> Objects.isNull(registro.getId()))
				                                         .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoResponsavel -> salvar(novoResponsavel));
		}

		//Atualiza os registros 
		vulnerabilidadesTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, uniformesAlunoTOBuilder.buildAll(uniformesEntregues))){
				salvar(registro);
			}
		});
	}
	
	
}
