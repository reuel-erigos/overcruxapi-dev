package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ResponsaveisAlunoTOBuilder;
import br.com.crux.dao.repository.ResponsaveisAlunoRepository;
import br.com.crux.entity.ResponsaveisAluno;
import br.com.crux.rule.CamposObrigatoriosResponsaveisAlunoRule;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.ResponsaveisAlunoTO;

@Component
public class AlterarResponsaveisAlunoCmd {

	@Autowired private ResponsaveisAlunoRepository repository;
	@Autowired private ResponsaveisAlunoTOBuilder responsaveisAlunoTOBuilder;
	@Autowired private CamposObrigatoriosResponsaveisAlunoRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetResponsavelFamiliarVigenteCmd getResponsavelFamiliarVigenteCmd;
	
	private void alterar(ResponsaveisAlunoTO respondavelTO, FamiliaresTO familiarTO) {
		camposObrigatoriosRule.verificar(respondavelTO);
		respondavelTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		ResponsaveisAluno entity = responsaveisAlunoTOBuilder.build(respondavelTO, familiarTO);
		repository.save(entity);
	}
	
	
	public void alterarAll(List<ResponsaveisAlunoTO> responsaveisTO, FamiliaresTO familiarTO) {
		//Lista de familiares do aluno.
		List<ResponsaveisAluno> responsaveisPorAluno = getResponsavelFamiliarVigenteCmd.getAllResponsaveis(familiarTO.getAluno().getId(), familiarTO.getId());
		
		BiPredicate<ResponsaveisAlunoTO, List<ResponsaveisAlunoTO>> contemNaLista  = (parte, lista) -> lista.stream()
																											.anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                 && 
																									                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		responsaveisPorAluno.removeIf(registro -> {
														if(!contemNaLista.test(responsaveisAlunoTOBuilder.buildTO(registro), responsaveisTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<ResponsaveisAlunoTO> novos = responsaveisTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoResponsavel -> alterar(novoResponsavel, familiarTO));
		}

		//Atualiza os registros 
		responsaveisTO.stream()
		              .filter(registro -> Objects.nonNull(registro.getId())) 
		              .forEach( registro -> {
												if(contemNaLista.test(registro, responsaveisAlunoTOBuilder.buildAll(responsaveisPorAluno))){
													alterar(registro, familiarTO);
												}
		});
	}
}
