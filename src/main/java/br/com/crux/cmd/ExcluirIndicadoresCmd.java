package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.IndicadoresRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirIndicadoresCmd {

	@Autowired
	private IndicadoresRepository indicadoresRepository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}
		indicadoresRepository.deleteById(id);
	}
}
