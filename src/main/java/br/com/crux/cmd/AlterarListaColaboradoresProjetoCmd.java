package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProjetoTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProjetoRepository;
import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ColaboradoresProjetoTO;

@Component
public class AlterarListaColaboradoresProjetoCmd extends AbstractAlterarListaCmd<ColaboradoresProjeto, ColaboradoresProjetoTO, Projeto> {

	@Autowired private ColaboradoresProjetoTOBuilder colaboradoresProjetoTOBuilder;
	@Autowired private GetColaboradoresProjetoCmd getColaboradoresProjetoCmd;
	@Autowired private CadastrarColaboradoresProjetoCmd cadastrarColaboradoresProjetoCmd;
	@Autowired private ColaboradoresProjetoRepository colaboradoresProjetoRepository;

	@Override
	protected ColaboradoresProjetoTO getTO(ColaboradoresProjeto entity) {
		return colaboradoresProjetoTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ColaboradoresProjetoTO> getTOListaBanco(List<ColaboradoresProjeto> lista) {
		return colaboradoresProjetoTOBuilder.buildAll(lista);

	}

	@Override
	protected List<ColaboradoresProjeto> getListaBanco(Projeto pai) {
		return getColaboradoresProjetoCmd.getPorProjeto(p);
	}

	@Override
	protected Long getIdentificadorTO(ColaboradoresProjetoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ColaboradoresProjetoTO to, Projeto p) {
		cadastrarColaboradoresProjetoCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(ColaboradoresProjeto registro) {
		colaboradoresProjetoRepository.delete(registro);

	}

}
