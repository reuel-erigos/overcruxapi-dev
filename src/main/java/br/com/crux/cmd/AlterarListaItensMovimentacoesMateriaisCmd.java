package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesMateriaisRepository;
import br.com.crux.entity.ItensMovimentacoesMateriais;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.to.ItensMovimentacoesMateriaisTO;

@Component
public class AlterarListaItensMovimentacoesMateriaisCmd extends AbstractAlterarListaCmd<ItensMovimentacoesMateriais, ItensMovimentacoesMateriaisTO, MovimentacoesMateriais> {

	@Autowired private ItensMovimentacoesMateriaisTOBuilder toBuilder;
	@Autowired private GetItensMovimentacoesMateriaisCmd getCmd;
	@Autowired private CadastrarItensMovimentacoesMateriaisCmd cadastrarCmd;
	@Autowired private ItensMovimentacoesMateriaisRepository repository;

	@Override
	protected ItensMovimentacoesMateriaisTO getTO(ItensMovimentacoesMateriais entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ItensMovimentacoesMateriaisTO> getTOListaBanco(List<ItensMovimentacoesMateriais> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<ItensMovimentacoesMateriais> getListaBanco(MovimentacoesMateriais pai) {
		return getCmd.getPorMovimentacoesMateriais(p);
	}

	@Override
	protected Long getIdentificadorTO(ItensMovimentacoesMateriaisTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ItensMovimentacoesMateriaisTO to, MovimentacoesMateriais p) {
		cadastrarCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(ItensMovimentacoesMateriais registro) {
		repository.delete(registro);

	}

}
