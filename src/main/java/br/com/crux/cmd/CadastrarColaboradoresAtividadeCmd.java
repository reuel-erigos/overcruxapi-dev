package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresAtividadeTOBuilder;
import br.com.crux.dao.repository.ColaboradoresAtividadeRepository;
import br.com.crux.entity.ColaboradoresAtividade;
import br.com.crux.rule.CamposObrigatoriosColaboradoresAtividadeRule;
import br.com.crux.to.ColaboradoresAtividadeTO;

@Component
public class CadastrarColaboradoresAtividadeCmd {

	@Autowired private ColaboradoresAtividadeRepository repository;
	@Autowired private ColaboradoresAtividadeTOBuilder colaboradoresAtividadeTOBuilder;
	@Autowired private CamposObrigatoriosColaboradoresAtividadeRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(ColaboradoresAtividadeTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setUsuariosSistema(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		ColaboradoresAtividade colaboradoresAtividade = colaboradoresAtividadeTOBuilder.build(to);
		repository.save(colaboradoresAtividade);
	}

	public void cadastrarAll(List<ColaboradoresAtividadeTO> colaboradoresAtividade, Long idAtividade) {
		colaboradoresAtividade.forEach(ca -> {ca.setIdAtividade(idAtividade); cadastrar(ca); });
	}

	
}
