package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeisEmpresas;
import br.com.crux.to.CategoriasContabeisEmpresasTO;

@Component
public class CategoriasContabeisEmpresasTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public CategoriasContabeisEmpresasTO buildTO(CategoriasContabeisEmpresas m) {
		CategoriasContabeisEmpresasTO to = new CategoriasContabeisEmpresasTO();

		if (Objects.isNull(m)) {return to;}
		BeanUtils.copyProperties(m, to);

		return to;
	}

	public CategoriasContabeisEmpresas build(CategoriasContabeisEmpresasTO to) {
		CategoriasContabeisEmpresas p = new CategoriasContabeisEmpresas();

		BeanUtils.copyProperties(to, p);
		
		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}
	
	public List<CategoriasContabeisEmpresasTO> buildAllTO(List<CategoriasContabeisEmpresas> list) {
		return list.stream().map(this::buildTO).collect(Collectors.toList());
	}
	
	public List<CategoriasContabeisEmpresas> buildAll(List<CategoriasContabeisEmpresasTO> list) {
		return list.stream().map(this::build).collect(Collectors.toList());
	}



}

