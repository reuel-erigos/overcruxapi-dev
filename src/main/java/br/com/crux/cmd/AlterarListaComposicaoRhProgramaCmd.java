package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProgramaTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProgramaRepository;
import br.com.crux.entity.ComposicaoRhPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ComposicaoRhProgramaTO;

@Component
public class AlterarListaComposicaoRhProgramaCmd extends AbstractAlterarListaCmd<ComposicaoRhPrograma, ComposicaoRhProgramaTO, Programa> {

	@Autowired private ComposicaoRhProgramaTOBuilder composicaoRhProgramaTOBuilder;
	@Autowired private ComposicaoRhProgramaRepository composicaoRhProgramaRepository;
	@Autowired private CadastrarComposicaoRhProgramaCmd cadastrarComposicaoRhProgramaCmd;

	@Override
	protected ComposicaoRhProgramaTO getTO(ComposicaoRhPrograma entity) {
		return composicaoRhProgramaTOBuilder.buildTO(entity);
	}

	@Override
	protected List<ComposicaoRhProgramaTO> getTOListaBanco(List<ComposicaoRhPrograma> lista) {
		return composicaoRhProgramaTOBuilder.buildAll(lista);
	}

	@Override
	protected List<ComposicaoRhPrograma> getListaBanco(Programa pai) {
		return composicaoRhProgramaRepository.findByPrograma(p).orElse(new ArrayList<ComposicaoRhPrograma>());
	}

	@Override
	protected Long getIdentificadorTO(ComposicaoRhProgramaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ComposicaoRhProgramaTO to, Programa p) {
		cadastrarComposicaoRhProgramaCmd.cadastrar(p, to);
	}

	@Override
	protected void deletar(ComposicaoRhPrograma registro) {
		composicaoRhProgramaRepository.delete(registro);

	}

}