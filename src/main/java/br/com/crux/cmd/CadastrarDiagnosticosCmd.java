package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.DiagnosticosRepository;
import br.com.crux.entity.Diagnosticos;
import br.com.crux.rule.CamposObrigatoriosDiagnosticosRule;
import br.com.crux.to.DiagnosticosTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarDiagnosticosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private DiagnosticosRepository repository;
	@Autowired private CamposObrigatoriosDiagnosticosRule camposObrigatoriosRule;
	
	
	public void cadastrar(DiagnosticosTO to) {
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		Diagnosticos entity = new Diagnosticos();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
