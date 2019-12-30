package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AtividadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirAtividadeCmd {

	@Autowired private AtividadeRepository repository;

	public void excluir(Long idAtividade) {

		
		try {
			if (Objects.isNull(idAtividade)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a Atividade. Parâmetro 'atividade' ausente.");
			}
		
			repository.deleteById(idAtividade);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a essa atividade.");
		}	
		

	}
}
