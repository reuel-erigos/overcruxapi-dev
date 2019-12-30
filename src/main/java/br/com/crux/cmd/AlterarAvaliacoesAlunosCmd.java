package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AvaliacoesAlunosTOBuilder;
import br.com.crux.dao.repository.AvaliacoesAlunosRepository;
import br.com.crux.entity.AvaliacoesAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAvaliacoesAlunosRule;
import br.com.crux.to.AvaliacoesAlunosTO;

@Component
public class AlterarAvaliacoesAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AvaliacoesAlunosRepository repository;
	@Autowired private CamposObrigatoriosAvaliacoesAlunosRule camposObrigatoriosRule;
	@Autowired private AvaliacoesAlunosTOBuilder avaliacoesAlunosTOBuilder;
	@Autowired private GetAvaliacoesAlunosCmd getAvaliacoesAlunosCmd;

	public void alterar(AvaliacoesAlunosTO to) {
		AvaliacoesAlunos entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Avaliação do aluno informada não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = avaliacoesAlunosTOBuilder.build(to);

		repository.save(entity);

	}
	
	
	
	private void salvar(AvaliacoesAlunosTO to) {
		camposObrigatoriosRule.verificar(to);
		
		AvaliacoesAlunos entity = null;
		if(Objects.nonNull(to.getId())) {
			entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Avaliação do aluno informada não existe.") );
		}
		
		entity = avaliacoesAlunosTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		repository.save(entity);
	}	
	

	public void alterarAll(List<AvaliacoesAlunosTO> avaliacoesTO, Long idAtividade, Long idAvaliacao) {
		//Lista de avaliações do aluno.
		List<AvaliacoesAlunos> listaBancoDados = getAvaliacoesAlunosCmd.getAllAlunosMatriculados(idAtividade, idAvaliacao);
		
		BiPredicate<AvaliacoesAlunosTO, List<AvaliacoesAlunosTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                          .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                                    		                      && 
                                                                                                    		                      registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		listaBancoDados.removeIf(registro -> {
												if(!contemNaLista.test(avaliacoesAlunosTOBuilder.buildTO(registro), avaliacoesTO)){
													repository.delete(registro); 
													return true;
												}
												return false;
							                 }
		                                );
		
		//Adiciona os novos registros
		List<AvaliacoesAlunosTO> novos = avaliacoesTO.stream()
				                                         .filter(registro -> Objects.isNull(registro.getId()))
				                                         .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoRegistro -> salvar(novoRegistro));
		}

		//Atualiza os registros 
		avaliacoesTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, avaliacoesAlunosTOBuilder.buildAll(listaBancoDados))){
				salvar(registro);
			}
		});
	}
		
}
