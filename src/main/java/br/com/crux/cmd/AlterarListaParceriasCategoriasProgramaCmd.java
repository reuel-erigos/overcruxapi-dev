package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasCategoriasTOBuilder;
import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class AlterarListaParceriasCategoriasProgramaCmd extends AbstractAlterarListaCmd<ParceriasCategorias, ParceriasCategoriasTO, ParceriasPrograma> {

	@Autowired private ParceriasCategoriasTOBuilder toBuilder;
	@Autowired private ParceriasCategoriasRepository repository;
	@Autowired private CadastrarParceriasCategoriasCmd cadastrarParceriasCategoriasCmd;

	@Override
	protected ParceriasCategoriasTO getTO(ParceriasCategorias entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ParceriasCategoriasTO> getTOListaBanco(List<ParceriasCategorias> lista) {
		return toBuilder.buildAll(lista);
	}

	@Override
	protected List<ParceriasCategorias> getListaBanco(ParceriasPrograma pai) {
		return repository.findByParceriasPrograma(p)
				.orElse(new ArrayList<ParceriasCategorias>());
	}

	@Override
	protected Long getIdentificadorTO(ParceriasCategoriasTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ParceriasCategoriasTO to, ParceriasPrograma p) {
		cadastrarParceriasCategoriasCmd.cadastrar(p, null, to);
	}

	@Override
	protected void deletar(ParceriasCategorias registro) {
		repository.delete(registro);

	}

}