package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.rule.CamposObrigatoriosAcaoRule;
import br.com.crux.to.AcaoTO;
import br.com.crux.to.GrupoAcoesSimlesTO;

@Component
public class CadastrarListaAcoesCmd {

	@Autowired private AcaoRepository repository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AcaoTOBuilder acaoTOBuilder;
	@Autowired private CadastrarMateriaisAcoesCmd cadastrarMateriaisAcoesCmd;
	@Autowired private CadastrarAnexosAcaoPlanejamentoCmd cadastrarAnexosAcaoPlanejamentoCmd;
	@Autowired private CamposObrigatoriosAcaoRule camposObrigatoriosRule;

	
	public void cadastrar(AcaoTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Acoes entity = acaoTOBuilder.build(to);
		
		entity = repository.save(entity);
		
		cadastrarMateriaisAcoesCmd.cadastrarAll(to.getMateriaisAcao(), entity.getId());
		cadastrarAnexosAcaoPlanejamentoCmd.cadastrarAll(to.getAnexos(), entity.getId());
	}

	
	public void cadastrarAll(List<AcaoTO> listaTO, Long idGrupoAcao) {
		listaTO.forEach(to -> {
			to.setGrupoAcao(new GrupoAcoesSimlesTO(idGrupoAcao));
			cadastrar(to); 
		});
	}
}
