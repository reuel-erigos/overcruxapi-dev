package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class AlterarContasCentrosCustoProjetoCmd
		extends AbstractAlterarListaCmd<ContasCentrosCusto, ContasCentrosCustoTO, ParceriasProjeto> {

	@Autowired
	private ContasCentrosCustoTOBuilder builder;
	@Autowired
	private ContasCentrosCustoRepository repository;
	@Autowired
	private CadastrarContasCentrosCustoCmd cadastrarCmd;
	@Autowired
	private ExcluirContasCentrosCustoCmd excluirCmd;
	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	

	@Override
	protected ContasCentrosCustoTO getTO(ContasCentrosCusto entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<ContasCentrosCustoTO> getTOListaBanco(List<ContasCentrosCusto> lista) {
		return builder.buildAllTO(lista);
	}

	@Override
	protected List<ContasCentrosCusto> getListaBanco(ParceriasProjeto pai) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		return repository.findByParceriasProjeto(idInstituicao, pai.getId()).orElse(new ArrayList<ContasCentrosCusto>());
	}

	@Override
	protected Long getIdentificadorTO(ContasCentrosCustoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ContasCentrosCustoTO to, ParceriasProjeto p) {
		cadastrarCmd.cadastrar(null, p, to);

	}

	@Override
	protected void deletar(ContasCentrosCusto registro) {
		excluirCmd.excluir(registro);
	}

}
