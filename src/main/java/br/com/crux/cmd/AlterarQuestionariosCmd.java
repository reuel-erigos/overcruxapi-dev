package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.QuestionariosRepository;
import br.com.crux.entity.Questionario;
import br.com.crux.enums.TipoQuestionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosQuestionariosRule;
import br.com.crux.to.QuestionarioTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarQuestionariosCmd {

	@Autowired private QuestionariosRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosQuestionariosRule camposObrigatoriosRule;
	
	
	public void alterar(QuestionarioTO to) {
		Optional<Questionario> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Questionario informado não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		Questionario entity = entityOptional.get();
		entity.setDescricao(to.getDescricao());
		
		Optional.ofNullable(to.getTipoQuestionario()).ifPresent(tq -> {
			entity.setTipoQuestionario(TipoQuestionario.getPorTipo(to.getTipoQuestionario()));
		});
		
		
		entity.setDataInicio(to.getDataInicio());
		entity.setDataFim(to.getDataFim());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
