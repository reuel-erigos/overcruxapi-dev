package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.to.RateiosMovimentacoesTO;

@Component
public class AlterarListaRateiosMovimentacoesCmd extends AbstractAlterarListaCmd<RateiosMovimentacoes, RateiosMovimentacoesTO, Movimentacoes> {

	@Autowired private RateiosMovimentacoesTOBuilder toBuilder;
	@Autowired private RateiosMovimentacoesRepository repository;
	@Autowired private CadastrarRateiosMovimentacoesCmd cadastrarCmd;
	@Autowired private GetRateiosMovimentacoesCmd getCmd;

	
	@Override
	protected RateiosMovimentacoesTO getTO(RateiosMovimentacoes entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<RateiosMovimentacoesTO> getTOListaBanco(List<RateiosMovimentacoes> lista) {
		return toBuilder.buildAllTO(lista);
	}

	@Override
	protected List<RateiosMovimentacoes> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(RateiosMovimentacoesTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(RateiosMovimentacoesTO to, Movimentacoes p) {
		cadastrarCmd.cadastrar(p.getId(), to);
	}

	@Override
	protected void deletar(RateiosMovimentacoes registro) {
		repository.delete(registro);
		repository.flush();
	}

}
