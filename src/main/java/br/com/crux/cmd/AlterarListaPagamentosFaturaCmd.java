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
		cadastrarCmd.cadastrar(to, p);
	}

	@Override
	protected void deletar(PagamentosFatura registro) {
		repository.delete(registro);

	}

}
