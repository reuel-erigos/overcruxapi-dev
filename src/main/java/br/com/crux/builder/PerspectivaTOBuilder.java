package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.Perspectiva;
import br.com.crux.entity.Unidade;
import br.com.crux.to.PerspectivaTO;

@Component
public class PerspectivaTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;

	public Perspectiva build(PerspectivaTO to) {
		Perspectiva entity = new Perspectiva();

		entity.setIdPerspectiva(to.getIdPerspectiva());
		entity.setNmPerspectiva(to.getNmPerspectiva());
		entity.setDtImplantacao(to.getDtImplantacao());
		entity.setDtTermino(to.getDtTermino());
		entity.setUsuarioAlteracao(to.getUsuariosAlteracao());

		Optional.ofNullable(to.getUnidade()).ifPresent(und->{
			Unidade unidade = getUnidadeCmd.getById(und.getIdUnidade());
			entity.setUnidade(unidade);
		});

		return entity;
	}

	public PerspectivaTO buildTO(Perspectiva dto) {
		PerspectivaTO to = new PerspectivaTO();
		
		if(Objects.isNull(dto)) {
			return to;
		}
		

		to.setIdPerspectiva(dto.getIdPerspectiva());
		to.setNmPerspectiva(dto.getNmPerspectiva());
		to.setDtImplantacao(dto.getDtImplantacao());
		to.setDtTermino(dto.getDtTermino());
		to.setUnidade(unidadeBuilder.buildTO(dto.getUnidade()));

		return to;
	}

	public List<PerspectivaTO> buildAll(List<Perspectiva> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
