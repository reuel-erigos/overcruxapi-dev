package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProgramaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProgramaRepository;
import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class AlterarListaColaboradoresProgramaCmd extends AbstractAlterarListaCmd<ColaboradoresPrograma, ColaboradoresProgramaTO, Programa> {

	@Autowired private ColaboradoresProgramaTOBuilder colaboradoresProgramaTOBuilder;
	@Autowired private GetColaboradoresProgramaCmd getColaboradoresProgramaCmd;
	@Autowired private CadastrarColaboradoresProgramaCmd cadastrarColaboradoresProgramaCmd;
	@Autowired private ColaboradoresProgramaRepository colaboradoresProgramaRepository;

	@Override
	protected ColaboradoresProgramaTO getTO(ColaboradoresPrograma entity) {
		return colaboradoresProgramaTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ColaboradoresProgramaTO> getTOListaBanco(List<ColaboradoresPrograma> lista) {
		return colaboradoresProgramaTOBuilder.buildAll(lista);

	}

	@Override
	protected List<ColaboradoresPrograma> getListaBanco(Programa pai) {
		return getColaboradoresProgramaCmd.getPorPrograma(p);
	}

	@Override
	protected Long getIdentificadorTO(ColaboradoresProgramaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ColaboradoresProgramaTO to, Programa p) {
		cadastrarColaboradoresProgramaCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(ColaboradoresPrograma registro) {
		colaboradoresProgramaRepository.delete(registro);

	}

}