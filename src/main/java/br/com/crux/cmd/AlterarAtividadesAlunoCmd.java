package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.dao.repository.AtividadesAlunoRepository;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAtividadesAlunoRule;
import br.com.crux.to.AtividadesAlunoTO;
import br.com.crux.to.TurmasTO;

@Component
public class AlterarAtividadesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AtividadesAlunoRepository repository;
	@Autowired private CamposObrigatoriosAtividadesAlunoRule camposObrigatoriosRule;
	@Autowired private AtividadesAlunoTOBuilder atividadesAlunoTOBuilder;
	@Autowired private CadastrarAtividadesAlunoCmd cadastrarAtividadesAlunoCmd;
	
	

	public void alterar(AtividadesAlunoTO to) {
		AtividadesAluno entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Oficina do Aluno informada não existe."));
		
		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity = atividadesAlunoTOBuilder.build(to);
		entity.setDataAlteracaoAtividade(LocalDateTime.now());
		repository.save(entity);
	}
	
	private void manter(AtividadesAlunoTO to) {
		if(Objects.nonNull(to.getId())) {
			alterar(to);
		} else {
			cadastrarAtividadesAlunoCmd.cadastrar(to);
		}
	}

	
	public void alterarAll(List<AtividadesAlunoTO> atividadesAlunoTO, TurmasTO turmaTO) {
		//Lista do BD.
		List<AtividadesAluno> atividadesAlunosBD = repository.findByTurma(turmaTO.getId()).orElse(new ArrayList<AtividadesAluno>());
		
		BiPredicate<AtividadesAlunoTO, List<AtividadesAlunoTO>> contemNaLista  = (parte, lista) -> lista.stream()
																											.anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                 && 
																									                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		atividadesAlunosBD.removeIf(registro -> {
														if(!contemNaLista.test(atividadesAlunoTOBuilder.buildTO(registro), atividadesAlunoTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<AtividadesAlunoTO> novos = atividadesAlunoTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> manter(novo));
		}

		//Atualiza os registros 
		atividadesAlunoTO.stream()
		              .filter(registro -> Objects.nonNull(registro.getId())) 
		              .forEach( registro -> {
												if(contemNaLista.test(registro, atividadesAlunoTOBuilder.buildAll(atividadesAlunosBD))){
													manter(registro);
												}
		});
	}	
}
