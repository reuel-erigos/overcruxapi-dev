package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.EmpresaRepository;
import br.com.crux.dao.repository.EntidadesSociaisRepository;
import br.com.crux.entity.EntidadesSociais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirEntidadesSociaisCmd {

	@Autowired private EntidadesSociaisRepository repository;
	@Autowired private EmpresaRepository empresaRepository;
	
	
	public void excluir(Long id) {
		
		try {
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
			}
			
			Optional<EntidadesSociais> entidadeSocial = repository.findById(id);
			if(!entidadeSocial.isPresent()) {
				throw new NotFoundException("Entidade Social informada não existe.");
			}
			
			empresaRepository.existsById(entidadeSocial.get().getEmpresa().getId());
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta entidade social.");
		}	
		
	}
}
