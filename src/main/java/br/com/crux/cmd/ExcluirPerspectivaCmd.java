package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirPerspectivaCmd {

	@Autowired
	private PerspectivaRepository perspectivaRepository;
	
	
	public void excluir(Long id) {
		try {
			
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a perspectiva. Parâmetro ausente.");
			}
			perspectivaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta perspectiva.");
		}	
		
	}
}
