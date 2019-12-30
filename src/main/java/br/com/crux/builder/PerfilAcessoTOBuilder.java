package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.PerfilAcesso;
import br.com.crux.to.PerfilAcessoTO;

@Component
public class PerfilAcessoTOBuilder {


	public PerfilAcesso build(PerfilAcessoTO to) {
		PerfilAcesso entity = new PerfilAcesso();
		
		entity.setId(to.getId());
		entity.setNmPerfilAcesso(to.getNome());
		entity.setAltera(to.getAltera());
		entity.setConsulta(to.getConsulta());
		entity.setDeleta(to.getDeleta());
		entity.setInsere(to.getInsere());
		entity.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return entity;
	}

	public PerfilAcessoTO buildTO(PerfilAcesso dto) {
		PerfilAcessoTO to = new PerfilAcessoTO();
		
		if(Objects.isNull(dto)) {
			return to;
		}
		

		to.setId(dto.getId());
		to.setNome(dto.getNmPerfilAcesso());
		to.setAltera(dto.getAltera());
		to.setConsulta(dto.getConsulta());
		to.setDeleta(dto.getDeleta());
		to.setInsere(dto.getInsere());
		to.setUsuarioAlteracao(dto.getUsuarioAlteracao());

		return to;
	}

	public List<PerfilAcessoTO> buildAll(List<PerfilAcesso> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
