package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirOficinasCmd {

	@Autowired private OficinasRepository repository;

	public void excluir(Long idAtividade) {

		
		try {
			if (Objects.isNull(idAtividade)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a Atividade. Parâmetro 'atividade' ausente.");
			}
		
			repository.deleteById(idAtividade);
		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}	

	}
}
