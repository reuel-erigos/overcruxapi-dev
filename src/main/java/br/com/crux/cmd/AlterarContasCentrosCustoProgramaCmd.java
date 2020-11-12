package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class AlterarContasCentrosCustoProgramaCmd
		extends AbstractAlterarListaCmd<ContasCentrosCusto, ContasCentrosCustoTO, ParceriasPrograma> {

	@Autowired
	private ContasCentrosCustoTOBuilder builder;
	@Autowired
	private ContasCentrosCustoRepository repository;
	@Autowired
	private CadastrarContasCentrosCustoCmd cadastrarCmd;
	@Autowired
	private ExcluirContasCentrosCustoCmd excluirCmd;

	@Override
	protected ContasCentrosCustoTO getTO(ContasCentrosCusto entity) {
		return builder.buildTO(entity);
	}

	@Override
	protected List<ContasCentrosCustoTO> getTOListaBanco(List<ContasCentrosCusto> lista) {
		return builder.buildAllTO(lista);
	}

	@Override
	protected List<ContasCentrosCusto> getListaBanco(ParceriasPrograma pai) {
		return repository.findByParceriasPrograma(pai).orElse(new ArrayList<ContasCentrosCusto>());
	}

	@Override
	protected Long getIdentificadorTO(ContasCentrosCustoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ContasCentrosCustoTO to, ParceriasPrograma p) {
		cadastrarCmd.cadastrar(p, null, to);

	}

	@Override
	protected void deletar(ContasCentrosCusto registro) {
		excluirCmd.excluir(registro);
	}

}
