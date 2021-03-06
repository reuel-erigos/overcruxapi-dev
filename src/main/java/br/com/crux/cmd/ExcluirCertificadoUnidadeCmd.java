package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CertificadoUnidadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirCertificadoUnidadeCmd {

	@Autowired
	private CertificadoUnidadeRepository repository;
	
	
	public void excluir(Long id) {
		try {
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
			}
			repository.deleteById(id);
		} catch (Exception e) {
			if(Objects.nonNull(e.getCause())) {
				if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
					throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
				}
			}

			throw new RuntimeException(e.getMessage());
		}	
		
	}
}
