package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasMovimentosTOBuilder;
import br.com.crux.dao.repository.CategoriasMovimentosRepository;
import br.com.crux.dao.repository.RateiosCategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosCategoriasMovimentos;
import br.com.crux.to.CategoriasMovimentosTO;

@Component
public class AlterarListaCategoriasMovimentosCmd extends AbstractAlterarListaCmd<CategoriasMovimentos, CategoriasMovimentosTO, Movimentacoes> {

	@Autowired private CategoriasMovimentosTOBuilder toBuilder;
	@Autowired private CategoriasMovimentosRepository repository;
	@Autowired private GetCategoriasMovimentosCmd getCmd;	
	@Autowired private CadastrarCategoriasMovimentosCmd cadastrarCmd;
	@Autowired private AlterarListaRateiosCategoriasMovimentosCmd alterarListaRateiosCmd;
	@Autowired private RateiosCategoriasMovimentosRepository rateiosCategoriasMovimentosRepository ; 
	

	@Override
	protected CategoriasMovimentosTO getTO(CategoriasMovimentos entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<CategoriasMovimentosTO> getTOListaBanco(List<CategoriasMovimentos> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<CategoriasMovimentos> getListaBanco(Movimentacoes pai) {
		return getCmd.getPorMovimentacoes(p);
	}

	@Override
	protected Long getIdentificadorTO(CategoriasMovimentosTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(CategoriasMovimentosTO to, Movimentacoes p) {
		CategoriasMovimentos entitySalva = cadastrarCmd.cadastrar(to, p);
		/*
		to.getRateioCategoriasMovimentos().forEach(r -> r.setIdCategoriaMovimento(entitySalva.getId()));
		
		alterarListaRateiosCmd.alterarAll(to.getRateioCategoriasMovimentos(), entitySalva);
		*/
	}

	@Override
	protected void deletar(CategoriasMovimentos registro) {
		/*
		Optional<List<RateiosCategoriasMovimentos>> rateios = rateiosCategoriasMovimentosRepository.findByIdCategoria(registro.getId());
		if(rateios.isPresent()) {
			rateiosCategoriasMovimentosRepository.deleteAll(rateios.get());
		}
		*/
		repository.delete(registro);
	}

}
