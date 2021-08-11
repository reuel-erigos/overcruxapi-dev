package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisEmpresasTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisEmpresasRepository;
import br.com.crux.entity.CategoriasContabeisEmpresas;
import br.com.crux.entity.Empresa;
import br.com.crux.to.CategoriasContabeisEmpresasTO;

@Component
public class AlterarListaCategoriasContabeisEmpresasCmd extends AbstractAlterarListaCmd<CategoriasContabeisEmpresas, CategoriasContabeisEmpresasTO, Empresa> {

	@Autowired private CadastrarCategoriasContabeisEmpresasCmd cadastrarCmd;
	@Autowired private GetCategoriasContabeisEmpresasCmd getCmd;
	@Autowired private CategoriasContabeisEmpresasTOBuilder toBuilder;
	@Autowired private CategoriasContabeisEmpresasRepository repository;

	@Override
	protected CategoriasContabeisEmpresasTO getTO(CategoriasContabeisEmpresas entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<CategoriasContabeisEmpresasTO> getTOListaBanco(List<CategoriasContabeisEmpresas> lista) {
		return toBuilder.buildAllTO(lista);

	}

	@Override
	protected List<CategoriasContabeisEmpresas> getListaBanco(Empresa pai) {
		return getCmd.getPorEmpresa(pai);
	}

	@Override
	protected Long getIdentificadorTO(CategoriasContabeisEmpresasTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(CategoriasContabeisEmpresasTO to, Empresa p) {
		cadastrarCmd.cadastrar(to, p);
	}

	@Override
	protected void deletar(CategoriasContabeisEmpresas registro) {
		repository.delete(registro);
	}

}
