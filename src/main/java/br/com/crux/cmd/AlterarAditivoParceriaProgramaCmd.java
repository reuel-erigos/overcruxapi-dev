package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaProgramaTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaProgramaRepository;
import br.com.crux.entity.AditivoParceriaPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.AditivoParceriaProgramaTO;

@Component
public class AlterarAditivoParceriaProgramaCmd
		extends AbstractAlterarListaCmd<AditivoParceriaPrograma, AditivoParceriaProgramaTO, ParceriasPrograma> {

	@Autowired
	private AditivoParceriaProgramaTOBuilder builder;
	@Autowired
	private AditivoParceriaProgramaRepository repository;
	@Autowired
	private ExcluirAditivoParceriaProgramaCmd excluirCmd;
	@Autowired
	private CadastrarAditivoParceriaProgramaCmd cadastrarCmd;

	@Override
	protected AditivoParceriaProgramaTO getTO(AditivoParceriaPrograma entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<AditivoParceriaProgramaTO> getTOListaBanco(List<AditivoParceriaPrograma> lista) {
		return builder.buildTO(lista);
	}

	@Override
	protected List<AditivoParceriaPrograma> getListaBanco(ParceriasPrograma pai) {
		return repository.findByParceriasPrograma(pai).orElse(new ArrayList<AditivoParceriaPrograma>());
	}

	@Override
	protected Long getIdentificadorTO(AditivoParceriaProgramaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(AditivoParceriaProgramaTO to, ParceriasPrograma p) {
		cadastrarCmd.cadastrar(p, to);

	}

	@Override
	protected void deletar(AditivoParceriaPrograma registro) {
		excluirCmd.excluir(registro.getId());

	}

}
