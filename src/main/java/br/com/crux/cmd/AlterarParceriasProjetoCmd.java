package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasProjetoTOBuilder;
import br.com.crux.dao.repository.ParceriasProjetoRepository;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ParceriasProjetoTO;

@Component
public class AlterarParceriasProjetoCmd extends AbstractAlterarListaCmd<ParceriasProjeto, ParceriasProjetoTO, Projeto> {

	@Autowired private ParceriasProjetoTOBuilder parceriasProjetoTOBuilder;
	@Autowired private ParceriasProjetoRepository parceriasProjetoRepository;
	@Autowired private ExcluirParceriasProjetoCmd excluirParceriasProjetoCmd;
	@Autowired private CadastrarParceriaProjetoCmd cadastrarParceriaProjetoCmd;
	
	

	@Override
	protected ParceriasProjetoTO getTO(ParceriasProjeto entity) {
		return parceriasProjetoTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ParceriasProjetoTO> getTOListaBanco(List<ParceriasProjeto> lista) {
		return parceriasProjetoTOBuilder.buildAll(lista);
	}

	@Override
	protected List<ParceriasProjeto> getListaBanco(Projeto pai) {
		return parceriasProjetoRepository.findByProjeto(pai).orElse(new ArrayList<ParceriasProjeto>());
	}

	@Override
	protected Long getIdentificadorTO(ParceriasProjetoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ParceriasProjetoTO to, Projeto p) {
		cadastrarParceriaProjetoCmd.cadastrar(p, to);
	}

	@Override
	protected void deletar(ParceriasProjeto registro) {
		excluirParceriasProjetoCmd.deletar(registro);
	}

}
