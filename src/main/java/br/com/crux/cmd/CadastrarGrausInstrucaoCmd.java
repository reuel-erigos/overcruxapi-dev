package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.GrausInstrucaoRepository;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.rule.CamposObrigatoriosGrausInstrucaoRule;
import br.com.crux.to.GrausInstrucaoTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarGrausInstrucaoCmd {


	@Autowired private GrausInstrucaoRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosGrausInstrucaoRule camposObrigatoriosRule;
	
	public void cadastrar(GrausInstrucaoTO to) {
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		GrausInstrucao entity = new GrausInstrucao();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
