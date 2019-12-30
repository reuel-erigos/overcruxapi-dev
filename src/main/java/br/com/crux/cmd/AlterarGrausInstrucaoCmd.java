package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.GrausInstrucaoRepository;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosGrausInstrucaoRule;
import br.com.crux.to.GrausInstrucaoTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarGrausInstrucaoCmd {

	@Autowired private GrausInstrucaoRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosGrausInstrucaoRule camposObrigatoriosRule;
	
	
	public void alterar(GrausInstrucaoTO to) {
		Optional<GrausInstrucao> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Grau de Instrucao informado não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		GrausInstrucao entity = entityOptional.get();
		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
