package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.dao.repository.AtividadesAlunoRepository;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.rule.CamposObrigatoriosAtividadesAlunoRule;
import br.com.crux.to.AtividadesAlunoTO;

@Component
public class CadastrarAtividadesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AtividadesAlunoRepository repository;
	@Autowired private CamposObrigatoriosAtividadesAlunoRule camposObrigatoriosRule;
	@Autowired private AtividadesAlunoTOBuilder atividadesAlunoTOBuilder;

	public void cadastrar(AtividadesAlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		AtividadesAluno entity = atividadesAlunoTOBuilder.build(to);
		entity.setDataCadastroAtividade(LocalDateTime.now());
		repository.save(entity);
	}
	
	public void cadastrarAll(List<AtividadesAlunoTO> atividadesAlunosTO) {
		atividadesAlunosTO.stream().forEach(atividadeTO -> {
			cadastrar(atividadeTO);
		});
	}	
	
}
