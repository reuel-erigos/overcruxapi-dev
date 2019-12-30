package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.UsuarioUnidadeDTO;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.to.DadosUsuarioUnidadeTO;

@Component
public class DadosUsuarioUnidadeTOBuilder {

	public DadosUsuarioUnidadeTO buildTO(UsuarioUnidadeDTO dto) {
		DadosUsuarioUnidadeTO to = new DadosUsuarioUnidadeTO();
		to.setIdUsuario(dto.getIdUsuario());
		to.setIdPessoaFisica(dto.getIdPessoaFisica());
		to.setEmail(dto.getEmail());
		to.setNomeCompleto(dto.getNomeCompleto());

		return to;
	}

	public UsuariosUnidade build(UsuariosSistema usuariosSistema, Unidade unidade) {
		UsuariosUnidade entity = new UsuariosUnidade();
		entity.setUnidade(unidade);
		entity.setUsuarioSistema(usuariosSistema);
		return entity;
	}

	public List<DadosUsuarioUnidadeTO> buildAll(List<UsuarioUnidadeDTO> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
