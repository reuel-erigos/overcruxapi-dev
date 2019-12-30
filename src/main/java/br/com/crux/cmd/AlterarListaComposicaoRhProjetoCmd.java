package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProjetoTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProjetoRepository;
import br.com.crux.entity.ComposicaoRhProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ComposicaoRhProjetoTO;

@Component
public class AlterarListaComposicaoRhProjetoCmd extends AbstractAlterarListaCmd<ComposicaoRhProjeto, ComposicaoRhProjetoTO, Projeto> {

	@Autowired private ComposicaoRhProjetoTOBuilder composicaoRhProjetoTOBuilder;
	@Autowired private ComposicaoRhProjetoRepository composicaoRhProjetoRepository;
	@Autowired private CadastrarComposicaoRhProjetoCmd cadastrarComposicaoRhProjetoCmd;

	@Override
	protected ComposicaoRhProjetoTO getTO(ComposicaoRhProjeto entity) {
		return composicaoRhProjetoTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ComposicaoRhProjetoTO> getTOListaBanco(List<ComposicaoRhProjeto> lista) {
		return composicaoRhProjetoTOBuilder.buildAll(lista);
	}

	@Override
	protected List<ComposicaoRhProjeto> getListaBanco(Projeto pai) {
		return composicaoRhProjetoRepository.findByProjeto(p).orElse(new ArrayList<ComposicaoRhProjeto>());
	}

	@Override
	protected Long getIdentificadorTO(ComposicaoRhProjetoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ComposicaoRhProjetoTO to, Projeto p) {
		cadastrarComposicaoRhProjetoCmd.cadastrar(p, to);
	}

	@Override
	protected void deletar(ComposicaoRhProjeto registro) {
		composicaoRhProjetoRepository.delete(registro);

	}

}
