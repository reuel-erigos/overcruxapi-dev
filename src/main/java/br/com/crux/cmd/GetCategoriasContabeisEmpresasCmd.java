package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisEmpresasTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisEmpresasRepository;
import br.com.crux.entity.CategoriasContabeisEmpresas;
import br.com.crux.entity.Empresa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CategoriasContabeisEmpresasTO;

@Component
public class GetCategoriasContabeisEmpresasCmd {

	@Autowired private CategoriasContabeisEmpresasTOBuilder toBuilder;
	@Autowired private CategoriasContabeisEmpresasRepository repository;

	public CategoriasContabeisEmpresas getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public CategoriasContabeisEmpresasTO getTOById(Long id) {
		Optional<CategoriasContabeisEmpresas> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Categoria contábil da emprsa não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<CategoriasContabeisEmpresasTO> getListaTOPorEmpresa(Empresa p) {
		Optional<List<CategoriasContabeisEmpresas>> lista = Optional.ofNullable(getPorEmpresa(p));
		return toBuilder.buildAllTO(lista.orElseGet(() -> new ArrayList<CategoriasContabeisEmpresas>()));
	}
	
	public List<CategoriasContabeisEmpresas> getPorEmpresa(Empresa p) {
		return repository.findByIdEmpresa(p.getId()).orElse(new ArrayList<CategoriasContabeisEmpresas>());
	}

}
