package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.DiagnosticosRepository;
import br.com.crux.entity.Diagnosticos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosDiagnosticosRule;
import br.com.crux.to.DiagnosticosTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarDiagnosticosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private DiagnosticosRepository repository;
	@Autowired private CamposObrigatoriosDiagnosticosRule camposObrigatoriosRule;
	
	
	
	public void alterar(DiagnosticosTO to) {
		Optional<Diagnosticos> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Diagnostico informado não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		Diagnosticos entity = entityOptional.get();
		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
