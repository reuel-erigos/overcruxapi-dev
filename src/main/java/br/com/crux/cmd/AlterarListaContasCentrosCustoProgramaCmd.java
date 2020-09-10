package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.Programa;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class AlterarListaContasCentrosCustoProgramaCmd extends AbstractAlterarListaCmd<ContasCentrosCusto, ContasCentrosCustoTO, Programa> {

	@Autowired private ContasCentrosCustoTOBuilder toBuilder;
	@Autowired private ContasCentrosCustoRepository repository;
	@Autowired private CadastrarContasCentrosCustoCmd cadastrarCmd;

	@Override
	protected ContasCentrosCustoTO getTO(ContasCentrosCusto entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ContasCentrosCustoTO> getTOListaBanco(List<ContasCentrosCusto> lista) {
		return toBuilder.buildAll(lista);
	}

	@Override
	protected List<ContasCentrosCusto> getListaBanco(Programa pai) {
		return repository.findByIdPrograma(pai.getId())
				.orElse(new ArrayList<ContasCentrosCusto>());
	}

	@Override
	protected Long getIdentificadorTO(ContasCentrosCustoTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ContasCentrosCustoTO to, Programa p) {
		cadastrarCmd.cadastrar(p, null, to);
	}

	@Override
	protected void deletar(ContasCentrosCusto registro) {
		repository.delete(registro);

	}

}