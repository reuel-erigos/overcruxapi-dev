package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ReembolsosPagamentosTOBuilder;
import br.com.crux.dao.repository.ReembolsosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class AlterarListaReembolsoPagamentosCmd extends AbstractAlterarListaCmd<ReembolsosPagamentos, ReembolsosPagamentosTO, PagamentosFatura> {

	@Autowired private ReembolsosPagamentosTOBuilder toBuilder;
	@Autowired private GetReembolsosPagamentosCmd getCmd;
	@Autowired private CadastrarReembolsosPagamentosCmd cadastrarCmd;
	@Autowired private ReembolsosPagamentosRepository repository;

	@Override
	protected ReembolsosPagamentosTO getTO(ReembolsosPagamentos entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ReembolsosPagamentosTO> getTOListaBanco(List<ReembolsosPagamentos> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<ReembolsosPagamentos> getListaBanco(PagamentosFatura pai) {
		return getCmd.getPorPagamentoFatura(pai);
	}

	@Override
	protected Long getIdentificadorTO(ReembolsosPagamentosTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ReembolsosPagamentosTO to, PagamentosFatura p) {
		cadastrarCmd.cadastrar(to, p);
	}

	@Override
	protected void deletar(ReembolsosPagamentos registro) {
		repository.delete(registro);
	}

}
