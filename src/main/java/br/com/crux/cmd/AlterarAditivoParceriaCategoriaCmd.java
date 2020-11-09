package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaCategoriaTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaCategoriaRepository;
import br.com.crux.entity.AditivoParceriaCategoria;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.to.AditivoParceriaCategoriaTO;

@Component
public class AlterarAditivoParceriaCategoriaCmd
		extends AbstractAlterarListaCmd<AditivoParceriaCategoria, AditivoParceriaCategoriaTO, ParceriasCategorias> {

	@Autowired
	private AditivoParceriaCategoriaTOBuilder builder;
	@Autowired
	private AditivoParceriaCategoriaRepository repository;
	@Autowired
	private ExcluirAditivoParceriaCategoriaCmd excluirCmd;
	@Autowired
	private CadastrarAditivoParceriaCategoriaCmd cadastrarCmd;

	@Override
	protected AditivoParceriaCategoriaTO getTO(AditivoParceriaCategoria entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<AditivoParceriaCategoriaTO> getTOListaBanco(List<AditivoParceriaCategoria> lista) {
		return builder.buildTO(lista);
	}

	@Override
	protected List<AditivoParceriaCategoria> getListaBanco(ParceriasCategorias pai) {
		return repository.findByParceriaCategoria(pai).orElse(new ArrayList<AditivoParceriaCategoria>());
	}

	@Override
	protected Long getIdentificadorTO(AditivoParceriaCategoriaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(AditivoParceriaCategoriaTO to, ParceriasCategorias p) {
		cadastrarCmd.cadastrar(p, to);

	}

	@Override
	protected void deletar(AditivoParceriaCategoria registro) {
		excluirCmd.excluir(registro.getId());

	}

}
