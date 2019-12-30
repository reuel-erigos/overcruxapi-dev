package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.VulnerabilidadesAlunoTOBuilder;
import br.com.crux.dao.repository.VulnerabilidadesAlunoRepository;
import br.com.crux.entity.VulnerabilidadesAluno;
import br.com.crux.rule.CamposObrigatoriosVulnerabilidadesAlunoRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.VulnerabilidadesAlunoTO;

@Component
public class AlterarVulnerabilidadesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private VulnerabilidadesAlunoRepository repository;
	@Autowired private CamposObrigatoriosVulnerabilidadesAlunoRule camposObrigatoriosRule;
	@Autowired private VulnerabilidadesAlunoTOBuilder vulnerabilidadesAlunoTOBuilder;
	@Autowired private GetVulnerabilidadesAlunoCmd getVulnerabilidadesAlunoCmd;
	

	private void alterar(VulnerabilidadesAlunoTO vulnerabilidadeTO, AlunoTO alunoTO) {
		camposObrigatoriosRule.verificar(vulnerabilidadeTO);
		vulnerabilidadeTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		VulnerabilidadesAluno entity = vulnerabilidadesAlunoTOBuilder.build(vulnerabilidadeTO, alunoTO);
		repository.save(entity);
	}
	
	
	public void alterarAll(List<VulnerabilidadesAlunoTO> vulnerabilidadesTO, AlunoTO alunoTO) {
		//Lista de vulnerabilidades do aluno.
		List<VulnerabilidadesAluno> vulnerabilidadesPorAluno = getVulnerabilidadesAlunoCmd.getAllAluno(alunoTO.getId());
		
		BiPredicate<VulnerabilidadesAlunoTO, List<VulnerabilidadesAlunoTO>> contemNaLista  = (parte, lista) -> lista.stream()
																													.anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																											                 && 
																											                 registroTO.getId().equals(parte.getId()));
		
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		vulnerabilidadesPorAluno.removeIf(registro -> {
														if(!contemNaLista.test(vulnerabilidadesAlunoTOBuilder.buildTO(registro), vulnerabilidadesTO)){
															repository.delete(registro); 
															return true;
														}
														return false;
									                }
		                                );
		
		//Adiciona os novos registros
		List<VulnerabilidadesAlunoTO> novos = vulnerabilidadesTO.stream()
				                                                .filter(registro -> Objects.isNull(registro.getId()))
				                                                .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novoResponsavel -> alterar(novoResponsavel, alunoTO));
		}

		//Atualiza os registros 
		vulnerabilidadesTO.stream()
		                  .filter(registro -> Objects.nonNull(registro.getId()))
		                  .forEach( registro -> {
													if(contemNaLista.test(registro, vulnerabilidadesAlunoTOBuilder.buildAll(vulnerabilidadesPorAluno))){
														alterar(registro, alunoTO);
													}
		});
	}

	
}
