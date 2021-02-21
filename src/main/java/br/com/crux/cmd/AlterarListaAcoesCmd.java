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
import br.com.crux.rule.CamposObrigatoriosAcaoRule;
import br.com.crux.to.AcaoTO;
import br.com.crux.to.GrupoAcoesSimlesTO;

@Component
public class AlterarListaAcoesCmd {

	@Autowired private AcaoRepository repository;
	@Autowired private AcaoTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosAcaoRule camposObrigatoriosRule;
	@Autowired private AlterarMateriaisAcoesCmd alterarMateriaisAcoesCmd;  
	@Autowired private AlterarAnexosAcaoPlanejamentoCmd alterarAnexosAcaoPlanejamentoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	private void alterar(AcaoTO to) {
		camposObrigatoriosRule.verificar(to);

		Acoes entity = toBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		
		alterarMateriaisAcoesCmd.alterarAll(to.getMateriaisAcao(), entity.getId());
		alterarAnexosAcaoPlanejamentoCmd.alterarAll(to.getAnexos(), entity.getId());
		
		repository.save(entity);
	}
	
	
	public void alterarAll(List<AcaoTO> listaTO, Long idGrupoAcao) {
		
		//Lista os registros no banco de dados
		List<Acoes> entitys = repository.findAllByIdGrupo(idGrupoAcao).orElse(new ArrayList<Acoes>());
		
		
		BiPredicate<AcaoTO, List<AcaoTO>> contemNaLista  = (parte, lista) -> lista.stream()
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
		List<AcaoTO> novos = listaTO.stream()
				                    .filter(registro -> Objects.isNull(registro.getId()))
				                    .collect(Collectors.toList());
		
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.setGrupoAcao(new GrupoAcoesSimlesTO(idGrupoAcao));
				alterar(novo);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
		       .filter(registro -> Objects.nonNull(registro.getId()))
		       .forEach( registro -> {
		                	
        	registro.setGrupoAcao(new GrupoAcoesSimlesTO(idGrupoAcao));
			if(contemNaLista.test(registro, toBuilder.buildAll(entitys))){
				alterar(registro);
			}
		});
	}
	
	
	
}
