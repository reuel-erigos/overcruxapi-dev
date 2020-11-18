package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AditivoParceriaProjetoRepository;
import br.com.crux.entity.AditivoParceriaProjeto;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirAditivoParceriaProjetoCmd {

	@Autowired
	private AditivoParceriaProjetoRepository repository;

	public void excluir(Long id) {

		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
			}
			repository.deleteById(id);

		} catch (Exception e) {
			if (Objects.nonNull(e.getCause())) {
				if (e.getCause() instanceof DataIntegrityViolationException
						|| e.getCause().toString().contains("ConstraintViolationException")) {
					throw new TabaleReferenciaEncontradaException(
							"Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
				}
			}

			throw new RuntimeException(e.getMessage());
		}

	}

	public void deletarAll(List<AditivoParceriaProjeto> lista) {
		repository.deleteInBatch(lista);

	}

}
