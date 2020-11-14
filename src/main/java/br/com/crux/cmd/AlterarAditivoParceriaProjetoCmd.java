package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaProjetoTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaProjetoRepository;
import br.com.crux.entity.AditivoParceriaProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.AditivoParceriaProjetoTO;

@Component
public class AlterarAditivoParceriaProjetoCmd
		extends AbstractAlterarListaCmd<AditivoParceriaProjeto, AditivoParceriaProjetoTO, ParceriasProjeto> {

	@Autowired
	private AditivoParceriaProjetoTOBuilder builder;
	@Autowired
	private AditivoParceriaProjetoRepository repository;
	@Autowired
	private ExcluirAditivoParceriaProjetoCmd excluirCmd;
	@Autowired
	private CadastrarAditivoParceriaProjetoCmd cadastrarCmd;

	@Override
	protected AditivoParceriaProjetoTO getTO(AditivoParceriaProjeto entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<AditivoParceriaProjetoTO> getTOListaBanco(List<AditivoParceriaProjeto> lista) {
		return builder.buildTO(lista);
	}

	@Override
	protected List<AditivoParceriaProjeto> getListaBanco(ParceriasProjeto pai) {
		return repository.findByParceriasProjeto(pai).orElse(new ArrayList<AditivoParceriaProjeto>());
	}

	@Override
	protected Long getIdentificadorTO(AditivoParceriaProjetoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(AditivoParceriaProjetoTO to, ParceriasProjeto p) {
		cadastrarCmd.cadastrar(p, to);

	}

	@Override
	protected void deletar(AditivoParceriaProjeto registro) {
		excluirCmd.excluir(registro.getId());

	}

}
