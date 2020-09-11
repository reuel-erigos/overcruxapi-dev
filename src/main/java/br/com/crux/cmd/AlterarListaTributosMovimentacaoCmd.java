package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributoMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributoMovimentacaoRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.TributosMovimentacoes;
import br.com.crux.to.TributosMovimentacoesTO;

@Component
public class AlterarListaTributosMovimentacaoCmd extends AbstractAlterarListaCmd<TributosMovimentacoes, TributosMovimentacoesTO, Movimentacoes> {

	@Autowired private TributoMovimentacaoTOBuilder toBuilder;
	@Autowired private GetTributoMovimentacaoCmd getCmd;
	@Autowired private CadastrarTributosMovimentacaoCmd cadastrarCmd;
	@Autowired private TributoMovimentacaoRepository repository;

	

	@Override
	protected TributosMovimentacoesTO getTO(TributosMovimentacoes entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<TributosMovimentacoesTO> getTOListaBanco(List<TributosMovimentacoes> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<TributosMovimentacoes> getListaBanco(Movimentacoes pai) {
		return getCmd.getAllByIdMovimentacao(p.getId());
	}

	@Override
	protected Long getIdentificadorTO(TributosMovimentacoesTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(TributosMovimentacoesTO to, Movimentacoes p) {
		cadastrarCmd.cadastrar(to, p.getId());
	}

	@Override
	protected void deletar(TributosMovimentacoes registro) {
		repository.delete(registro);

	}

}
