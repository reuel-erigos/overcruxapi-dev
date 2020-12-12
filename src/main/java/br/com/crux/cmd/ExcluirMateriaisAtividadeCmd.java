package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MateriaisAtividadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirMateriaisAtividadeCmd {

	@Autowired
	private MateriaisAtividadeRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o material da atividade. Parâmetro ausente.");
		}
		repository.deleteById(id);
	}
}
