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
public class AlterarParceriasCategoriasProgramaCmd
		extends AbstractAlterarListaCmd<ParceriasCategorias, ParceriasCategoriasTO, ParceriasPrograma> {

	@Autowired
	private ParceriasCategoriasTOBuilder builder;
	@Autowired
	private ParceriasCategoriasRepository repository;
	@Autowired
	private CadastrarParceriasCategoriasCmd cadastrarCmd;
	@Autowired
	private ExcluirParceriasCategoriasCmd excluirCmd;

	@Override
	protected ParceriasCategoriasTO getTO(ParceriasCategorias entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<ParceriasCategoriasTO> getTOListaBanco(List<ParceriasCategorias> lista) {
		return builder.buildAllTO(lista);
	}

	@Override
	protected List<ParceriasCategorias> getListaBanco(ParceriasPrograma pai) {
		return repository.findByParceriasPrograma(pai).orElse(new ArrayList<ParceriasCategorias>());
	}

	@Override
	protected Long getIdentificadorTO(ParceriasCategoriasTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ParceriasCategoriasTO to, ParceriasPrograma p) {
		cadastrarCmd.cadastrar(p, null, to);

	}

	@Override
	protected void deletar(ParceriasCategorias registro) {
		excluirCmd.excluir(registro);

	}

}
