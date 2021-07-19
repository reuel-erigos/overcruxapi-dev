package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesContabeisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesContabeisRepository;
import br.com.crux.entity.MovimentacoesContabeis;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesContabeisRule;
import br.com.crux.to.MovimentacoesContabeisTO;

@Component
public class CadastrarMovimentacoesContabeisCmd {

	@Autowired private MovimentacoesContabeisRepository repository;
	@Autowired private MovimentacoesContabeisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesContabeisRule rule;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public void cadastrar(MovimentacoesContabeisTO to) {
		rule.verificar(to);
		MovimentacoesContabeis entity = toBuilder.build(to);
		entity.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		
		repository.save(entity);
	}
}
