package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasProgramaTOBuilder;
import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ParceriasProgramaTO;

@Component
public class AlterarParceriasProgramaCmd extends AbstractAlterarListaCmd<ParceriasPrograma, ParceriasProgramaTO, Programa> {

	@Autowired private ParceriasProgramaTOBuilder parceriasProgramaTOBuilder;
	@Autowired private ParceriasProgramaRepository parceriasProgramaRepository;
	@Autowired private ExcluirParceriasProgramaCmd excluirParceriasProgramaCmd;
	@Autowired private CadastrarParceriaProgramaCmd cadastrarParceriaProgramaCmd;

	@Override
	protected ParceriasProgramaTO getTO(ParceriasPrograma entity) {
		return parceriasProgramaTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ParceriasProgramaTO> getTOListaBanco(List<ParceriasPrograma> lista) {
		return parceriasProgramaTOBuilder.buildAll(lista);
	}

	@Override
	protected List<ParceriasPrograma> getListaBanco(Programa pai) {
		return parceriasProgramaRepository.findByPrograma(pai).orElse(new ArrayList<ParceriasPrograma>());
	}

	@Override
	protected Long getIdentificadorTO(ParceriasProgramaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ParceriasProgramaTO to, Programa p) {
		cadastrarParceriaProgramaCmd.cadastrar(p, to);
	}

	@Override
	protected void deletar(ParceriasPrograma registro) {
		excluirParceriasProgramaCmd.deletar(registro);
	}

}
