package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesMateriaisRepository;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesMateriaisRule;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class AlterarMovimentacoesMateriaisCmd {

	@Autowired private MovimentacoesMateriaisRepository repository;
	@Autowired private MovimentacoesMateriaisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesMateriaisRule rule;
	@Autowired private AlterarListaItensMovimentacoesMateriaisCmd alterarListaItensMovimentacoesMateriaisCmd;

	public void alterar(MovimentacoesMateriaisTO to) {
		MovimentacoesMateriais entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);

		entity = toBuilder.build(to);

		MovimentacoesMateriais movimentacoesMateriais = repository.save(entity);
		
		alterarListaItensMovimentacoesMateriaisCmd.alterarAll(to.getItensMovimentacoesMateriais(), movimentacoesMateriais);

	}

}
