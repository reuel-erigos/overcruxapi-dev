package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisEmpresasTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisEmpresasRepository;
import br.com.crux.entity.CategoriasContabeisEmpresas;
import br.com.crux.entity.Empresa;
import br.com.crux.to.CategoriasContabeisEmpresasTO;

@Component
public class CadastrarCategoriasContabeisEmpresasCmd {

	@Autowired private CategoriasContabeisEmpresasRepository repository;
	@Autowired private CategoriasContabeisEmpresasTOBuilder tOBuilder;

	public CategoriasContabeisEmpresas cadastrar(CategoriasContabeisEmpresasTO to, Empresa pai) {
		to.setIdEmpresa(pai.getId());
		CategoriasContabeisEmpresas entity = tOBuilder.build(to);
		return repository.save(entity);
	}

	public List<CategoriasContabeisEmpresas> cadastrarLista(Empresa pai, List<CategoriasContabeisEmpresasTO> lista) {
		return lista.stream().map(item -> cadastrar(item, pai)).collect(Collectors.toList());

	}

}
