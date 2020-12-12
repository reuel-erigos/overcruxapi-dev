package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirPerspectivaCmd {

	@Autowired
	private PerspectivaRepository perspectivaRepository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir a perspectiva. Parâmetro ausente.");
		}
		perspectivaRepository.deleteById(id);
	}
}
