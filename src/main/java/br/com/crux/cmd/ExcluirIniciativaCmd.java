package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.IniciativaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirIniciativaCmd {

	@Autowired
	private IniciativaRepository iniciativaRepository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}
		iniciativaRepository.deleteById(id);
	}
}
