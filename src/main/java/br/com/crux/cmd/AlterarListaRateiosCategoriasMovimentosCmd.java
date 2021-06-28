package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosCategoriasMovimentosTOBuilder;
import br.com.crux.dao.repository.RateiosCategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.RateiosCategoriasMovimentos;
import br.com.crux.to.RateiosCategoriasMovimentosTO;

@Component
public class AlterarListaRateiosCategoriasMovimentosCmd extends AbstractAlterarListaCmd<RateiosCategoriasMovimentos, RateiosCategoriasMovimentosTO, CategoriasMovimentos> {

	@Autowired private CadastrarRateiosCategoriasMovimentosCmd cadastrarCmd;
	@Autowired private GetRateiosCategoriasMovimentosCmd getCmd;
	@Autowired private RateiosCategoriasMovimentosTOBuilder toBuilder;
	@Autowired private RateiosCategoriasMovimentosRepository repository;

	@Override
	protected RateiosCategoriasMovimentosTO getTO(RateiosCategoriasMovimentos entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<RateiosCategoriasMovimentosTO> getTOListaBanco(List<RateiosCategoriasMovimentos> lista) {
		return toBuilder.buildAllTO(lista);

	}

	@Override
	protected List<RateiosCategoriasMovimentos> getListaBanco(CategoriasMovimentos pai) {
		return getCmd.getPorCategoriaMovimento(pai);
	}

	@Override
	protected Long getIdentificadorTO(RateiosCategoriasMovimentosTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(RateiosCategoriasMovimentosTO to, CategoriasMovimentos p) {
		cadastrarCmd.cadastrar(to, p);
	}

	@Override
	protected void deletar(RateiosCategoriasMovimentos registro) {
		repository.delete(registro);
	}

}
