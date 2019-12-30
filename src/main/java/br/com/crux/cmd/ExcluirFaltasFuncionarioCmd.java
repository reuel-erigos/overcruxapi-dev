package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.FaltasFuncionarioRepository;
import br.com.crux.entity.FaltasFuncionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirFaltasFuncionarioCmd {

	@Autowired private FaltasFuncionarioRepository repository;
	
	public void excluir(Long id) {
		
		try {
			
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir.");
			}
			
			Optional<FaltasFuncionario> entity = repository.findById(id);
			if(!entity.isPresent()) {
				throw new NotFoundException("Falta do Funcionario informada não existe.");
			}
			
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta falta.");
		}	
		
	}
}
