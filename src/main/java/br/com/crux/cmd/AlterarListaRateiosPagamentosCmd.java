package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosPagamentosTOBuilder;
import br.com.crux.dao.repository.RateiosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.to.RateiosPagamentosTO;

@Component
public class AlterarListaRateiosPagamentosCmd extends AbstractAlterarListaCmd<RateiosPagamentos, RateiosPagamentosTO, PagamentosFatura> {

	@Autowired private RateiosPagamentosTOBuilder toBuilder;
	@Autowired private GetRateiosPagamentosCmd getCmd;
	@Autowired private CadastrarRateiosPagamentosCmd cadastrarCmd;
	@Autowired private RateiosPagamentosRepository repository;

	@Override
	protected RateiosPagamentosTO getTO(RateiosPagamentos entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<RateiosPagamentosTO> getTOListaBanco(List<RateiosPagamentos> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<RateiosPagamentos> getListaBanco(PagamentosFatura pai) {
		return getCmd.getPorPagamentoFatura(pai);
	}

	@Override
	protected Long getIdentificadorTO(RateiosPagamentosTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(RateiosPagamentosTO to, PagamentosFatura p) {
		cadastrarCmd.cadastrar(to, p);
	}

	@Override
	protected void deletar(RateiosPagamentos registro) {
		repository.delete(registro);
	}

}
