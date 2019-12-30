package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresAtividadeTOBuilder;
import br.com.crux.dao.repository.ColaboradoresAtividadeRepository;
import br.com.crux.entity.ColaboradoresAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosColaboradoresAtividadeRule;
import br.com.crux.to.AtividadesTO;
import br.com.crux.to.ColaboradoresAtividadeTO;

@Component
public class AlterarColaboradesAtividadeCmd {

	@Autowired private ColaboradoresAtividadeRepository repository;
	@Autowired private GetColaboradoresAtividadeCmd getColaboradoresAtividadeCmd;
	@Autowired private ColaboradoresAtividadeTOBuilder colaboradoresAtividadeTOBuilder;
	@Autowired private CadastrarColaboradoresAtividadeCmd cadastrarColaboradoresAtividadeCmd;
	@Autowired private CamposObrigatoriosColaboradoresAtividadeRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetAtividadeCmd getAtividadeCmd;
	 
	
	private void alterar(ColaboradoresAtividadeTO to, Long idAtividade) {
		camposObrigatoriosRule.verificarAtualizar(to);
		
		ColaboradoresAtividade entity = null;
		if(Objects.nonNull(to.getId())) {
			entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Colaboradores atividade informado não existe.") );
		}
		entity = colaboradoresAtividadeTOBuilder.buildComIdAtividade(to,idAtividade);
		entity.setUsuariosSistema(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		repository.save(entity);
	}	
	
	
	public void alterarAll(List<ColaboradoresAtividadeTO> colaboradoresAtividadeTO, Long idAtividade) {
		
		//Lista de colaboradores da atividade.
		List<ColaboradoresAtividade> colaboradoresAtividade = getColaboradoresAtividadeCmd.getAllPorAtividade(idAtividade);
		
		BiPredicate<ColaboradoresAtividadeTO, List<ColaboradoresAtividadeTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                                      .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                                    		                 && 
                                                                                                    		                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		colaboradoresAtividade.removeIf(registro -> {
														if(!contemNaLista.test(colaboradoresAtividadeTOBuilder.buildTO(registro), colaboradoresAtividadeTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<ColaboradoresAtividadeTO> novos = colaboradoresAtividadeTO.stream()
				                                         .filter(registro -> Objects.isNull(registro.getId()))
				                                         .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			AtividadesTO atividadeTO = getAtividadeCmd.getTOById(idAtividade);
			novos.forEach(novoColaborador -> {
				novoColaborador.setIdAtividade(atividadeTO.getId());
				cadastrarColaboradoresAtividadeCmd.cadastrar(novoColaborador);
			});
		}

		//Atualiza os registros 
		colaboradoresAtividadeTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
			if(contemNaLista.test(registro, colaboradoresAtividadeTOBuilder.buildAll(colaboradoresAtividade))){
				alterar(registro,idAtividade);
			}
		});
	}
	

	
	
}
