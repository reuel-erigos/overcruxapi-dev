package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MateriaisAtividadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirMateriaisAtividadeCmd {

	@Autowired
	private MateriaisAtividadeRepository repository;
	
	
	public void excluir(Long id) {
		try {
			
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir o material da atividade. Parâmetro ausente.");
			}
			repository.deleteById(id);
		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}	
		
	}
}
