package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DocumentosAtividadeTOBuilder;
import br.com.crux.dao.repository.DocumentosAtividadeRepository;
import br.com.crux.entity.DocumentosAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosDocumentosAtividadeRule;
import br.com.crux.to.DocumentosAtividadeTO;

@Component
public class AlterarDocumentosAtividadeCmd {

	@Autowired private DocumentosAtividadeRepository repository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private DocumentosAtividadeTOBuilder documentosAtividadeTOBuilder;

	@Autowired private CamposObrigatoriosDocumentosAtividadeRule camposObrigatoriosRule;

	public void alterar(DocumentosAtividadeTO to) {
		camposObrigatoriosRule.verificar(to);

		DocumentosAtividade entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Documento da Atividade não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = documentosAtividadeTOBuilder.build(to);

		repository.save(entity);

	}
}
