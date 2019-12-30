package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DocumentosAtividadeTOBuilder;
import br.com.crux.dao.repository.DocumentosAtividadeRepository;
import br.com.crux.entity.DocumentosAtividade;
import br.com.crux.rule.CamposObrigatoriosDocumentosAtividadeRule;
import br.com.crux.to.DocumentosAtividadeTO;

@Component
public class CadastrarDocumentosAtividadeCmd {

	@Autowired private DocumentosAtividadeRepository repository;
	@Autowired private DocumentosAtividadeTOBuilder documentosAtividadeTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private CamposObrigatoriosDocumentosAtividadeRule camposObrigatoriosRule;

	public void cadastrar(DocumentosAtividadeTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		DocumentosAtividade documentosAtividade = documentosAtividadeTOBuilder.build(to);

		repository.save(documentosAtividade);

	}
}
