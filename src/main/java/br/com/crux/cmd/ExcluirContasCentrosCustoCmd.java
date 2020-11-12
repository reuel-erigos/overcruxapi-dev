package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;

@Component
public class ExcluirContasCentrosCustoCmd {

	@Autowired
	ContasCentrosCustoRepository repository;

	public void excluir(ContasCentrosCusto registro) {
		repository.delete(registro);
	}

	public void deletarAll(List<ContasCentrosCusto> lista) {

		repository.deleteInBatch(lista);

	}

}
