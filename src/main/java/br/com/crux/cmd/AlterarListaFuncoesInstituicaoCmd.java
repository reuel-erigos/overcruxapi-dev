package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesInstituicaoTOBuilder;
import br.com.crux.dao.repository.FuncoesInstituicaoRepository;
import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class AlterarListaFuncoesInstituicaoCmd extends AbstractAlterarListaCmd<FuncoesInstituicao, FuncoesInstituicaoTO, Instituicao> {

	@Autowired private FuncoesInstituicaoTOBuilder toBuilder;
	@Autowired private GetFuncoesInstituicaoCmd getCmd;
	@Autowired private CadastrarFuncoesInstituicaoCmd cadastrarCmd;
	@Autowired private FuncoesInstituicaoRepository repository;

	@Override
	protected FuncoesInstituicaoTO getTO(FuncoesInstituicao entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<FuncoesInstituicaoTO> getTOListaBanco(List<FuncoesInstituicao> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<FuncoesInstituicao> getListaBanco(Instituicao pai) {
		return getCmd.getPorInstituicao(p);
	}

	@Override
	protected Long getIdentificadorTO(FuncoesInstituicaoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(FuncoesInstituicaoTO to, Instituicao p) {
		cadastrarCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(FuncoesInstituicao registro) {
		repository.delete(registro);

	}

}
