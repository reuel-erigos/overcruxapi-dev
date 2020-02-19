package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesMateriaisRepository;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesMateriaisRule;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class CadastrarMovimentacoesMateriaisCmd {

	@Autowired private MovimentacoesMateriaisRepository repository;
	@Autowired private MovimentacoesMateriaisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesMateriaisRule rule;
	@Autowired private CadastrarItensMovimentacoesMateriaisCmd cadastrarItensMovimentacoesMateriaisCmd;
	

	public void cadastrar(MovimentacoesMateriaisTO to) {

		rule.verificar(to);

		MovimentacoesMateriais entity = toBuilder.build(to);

		MovimentacoesMateriais movimentacoesMateriais = repository.save(entity);
		
		cadastrarItensMovimentacoesMateriaisCmd.cadastrarLista(movimentacoesMateriais, to.getItensMovimentacoesMateriais());

	}
}
