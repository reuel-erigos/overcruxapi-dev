package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AditivoParceriaCategoriaRepository;
import br.com.crux.entity.AditivoParceriaCategoria;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirAditivoParceriaCategoriaCmd {

	@Autowired
	private AditivoParceriaCategoriaRepository repository;

	public void excluir(Long id) {

		if (Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}
		repository.deleteById(id);
	}

	public void deletarAll(List<AditivoParceriaCategoria> lista) {
		repository.deleteInBatch(lista);

	}

}
