package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesRepository;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.ItensMovimentacoesTO;

@Component
public class AlterarListaItensMovimentacoesCmd extends AbstractAlterarListaCmd<ItensMovimentacoes, ItensMovimentacoesTO, Movimentacoes> {

	@Autowired private ItensMovimentacoesTOBuilder toBuilder;
	@Autowired private GetItensMovimentacoesCmd getCmd;
	@Autowired private CadastrarItensMovimentacoesCmd cadastrarCmd;
	@Autowired private ItensMovimentacoesRepository repository;

	@Override
	protected ItensMovimentacoesTO getTO(ItensMovimentacoes entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ItensMovimentacoesTO> getTOListaBanco(List<ItensMovimentacoes> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<ItensMovimentacoes> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(ItensMovimentacoesTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ItensMovimentacoesTO to, Movimentacoes p) {
		cadastrarCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(ItensMovimentacoes registro) {
		repository.delete(registro);

	}

}
