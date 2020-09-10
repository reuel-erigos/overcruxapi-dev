package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PagamentosFaturaTOBuilder;
import br.com.crux.dao.repository.PagamentosFaturaRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class AlterarListaPagamentosFaturaCmd extends AbstractAlterarListaCmd<PagamentosFatura, PagamentosFaturaTO, Movimentacoes> {

	@Autowired private PagamentosFaturaTOBuilder toBuilder;
	@Autowired private GetPagamentosFaturaCmd getCmd;
	@Autowired private CadastrarPagamentosFaturaCmd cadastrarCmd;
	@Autowired private PagamentosFaturaRepository repository;
	@Autowired private AlterarListaReembolsoPagamentosCmd alterarListaReembolsoPagamentosCmd;
	@Autowired private AlterarListaRateiosPagamentosCmd alterarListaRateiosPagamentosCmd;

	

	@Override
	protected PagamentosFaturaTO getTO(PagamentosFatura entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<PagamentosFaturaTO> getTOListaBanco(List<PagamentosFatura> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<PagamentosFatura> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(PagamentosFaturaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(PagamentosFaturaTO to, Movimentacoes p) {
		PagamentosFatura entitySalva = cadastrarCmd.cadastrar(to, p);
		to.getReembolsos().forEach(r -> r.setIdPagamentoFatura(entitySalva.getId()));
		to.getRateioPagamento().forEach(r -> r.setIdPagamentoFatura(entitySalva.getId()));
		
		alterarListaReembolsoPagamentosCmd.alterarAll(to.getReembolsos(), entitySalva);
		alterarListaRateiosPagamentosCmd.alterarAll(to.getRateioPagamento(), entitySalva);
	}

	@Override
	protected void deletar(PagamentosFatura registro) {
		repository.delete(registro);

	}

}
