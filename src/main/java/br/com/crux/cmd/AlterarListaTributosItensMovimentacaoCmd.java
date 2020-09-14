package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributosItensMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributosItensMovimentacaoRepository;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.TributosItensMovimentacoes;
import br.com.crux.to.TributosItensMovimentacoesTO;

@Component
public class AlterarListaTributosItensMovimentacaoCmd extends AbstractAlterarListaCmd<TributosItensMovimentacoes, TributosItensMovimentacoesTO, ItensMovimentacoes> {

	@Autowired private TributosItensMovimentacaoTOBuilder toBuilder;
	@Autowired private GetTributosItensMovimentacaoCmd getCmd;
	@Autowired private CadastrarTributosItensMovimentacaoCmd cadastrarCmd;
	@Autowired private TributosItensMovimentacaoRepository repository;

	

	@Override
	protected TributosItensMovimentacoesTO getTO(TributosItensMovimentacoes entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<TributosItensMovimentacoesTO> getTOListaBanco(List<TributosItensMovimentacoes> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<TributosItensMovimentacoes> getListaBanco(ItensMovimentacoes pai) {
		return getCmd.getAllByIdItemMovimentacao(p.getId());
	}

	@Override
	protected Long getIdentificadorTO(TributosItensMovimentacoesTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(TributosItensMovimentacoesTO to, ItensMovimentacoes p) {
		cadastrarCmd.cadastrar(to, p.getId());
	}

	@Override
	protected void deletar(TributosItensMovimentacoes registro) {
		repository.delete(registro);

	}

}
