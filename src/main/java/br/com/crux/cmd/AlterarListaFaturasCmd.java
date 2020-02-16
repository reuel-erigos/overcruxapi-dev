package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaturaTOBuilder;
import br.com.crux.dao.repository.FaturaRepository;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.FaturaTO;

@Component
public class AlterarListaFaturasCmd extends AbstractAlterarListaCmd<Fatura, FaturaTO, Movimentacoes> {

	@Autowired private FaturaTOBuilder toBuilder;
	@Autowired private GetFaturaCmd getCmd;
	@Autowired private CadastrarFaturasCmd cadastrarCmd;
	@Autowired private FaturaRepository repository;

	@Override
	protected FaturaTO getTO(Fatura entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<FaturaTO> getTOListaBanco(List<Fatura> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<Fatura> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(FaturaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(FaturaTO to, Movimentacoes p) {
		cadastrarCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(Fatura registro) {
		repository.delete(registro);

	}

}
