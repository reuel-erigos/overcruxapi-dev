package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesUnidadesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesUnidadesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.to.RateiosMovimentacoesUnidadesTO;

@Component
public class AlterarListaRateiosMovimentacoesUnidadesCmd extends AbstractAlterarListaCmd<RateiosMovimentacoesUnidades, RateiosMovimentacoesUnidadesTO, Movimentacoes> {

	@Autowired private RateiosMovimentacoesUnidadesTOBuilder toBuilder;
	@Autowired private RateiosMovimentacoesUnidadesRepository repository;
	@Autowired private CadastrarRateiosMovimentacoesUnidadesCmd cadastrarCmd;
	@Autowired private GetRateiosMovimentacoesUnidadesCmd getCmd;

	
	@Override
	protected RateiosMovimentacoesUnidadesTO getTO(RateiosMovimentacoesUnidades entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<RateiosMovimentacoesUnidadesTO> getTOListaBanco(List<RateiosMovimentacoesUnidades> lista) {
		return toBuilder.buildAllTO(lista);
	}

	@Override
	protected List<RateiosMovimentacoesUnidades> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(RateiosMovimentacoesUnidadesTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(RateiosMovimentacoesUnidadesTO to, Movimentacoes p) {
		cadastrarCmd.cadastrar(p.getId(), to);
	}

	@Override
	protected void deletar(RateiosMovimentacoesUnidades registro) {
		repository.delete(registro);
		repository.flush();
	}

}
