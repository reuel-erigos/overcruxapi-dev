package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Unidade;
import br.com.crux.to.DepartamentoTO;

@Component
public class DepartamentoTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;

	public Departamentos build(DepartamentoTO to) {
		Departamentos entity = new Departamentos();
		
		entity.setIdDepartamento(to.getIdDepartamento());
		entity.setCdUnidadeDepartamento(to.getCdUnidadeDepartamento());
		entity.setDsEnderecoDepartamento(to.getDsEnderecoDepartamento());
		entity.setNmDepartamento(to.getNmDepartamento());
		entity.setNrTelefoneDepartamento(to.getNrTelefoneDepartamento());
		
		Optional.ofNullable(to.getUnidade()).ifPresent(u -> {
			Unidade unidade = getUnidadeCmd.getById(u.getIdUnidade());
			entity.setUnidade(unidade);
		});
		
		if (Objects.nonNull(to.getDepartamentoSuperior()) && Objects.nonNull(to.getDepartamentoSuperior().getIdDepartamento()) ) {
			Departamentos departamento = getDepartamentoCmd.getById(to.getDepartamentoSuperior().getIdDepartamento());
			entity.setDepartamentosSuperior(departamento);
		}
		
		entity.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return entity;
	}

	public DepartamentoTO buildTO(Departamentos d) {
		DepartamentoTO to = new DepartamentoTO();

		if (Objects.isNull(d)) {
			return to;
		}

		to.setIdDepartamento(d.getIdDepartamento());
		to.setCdUnidadeDepartamento(d.getCdUnidadeDepartamento());
		to.setDsEnderecoDepartamento(d.getDsEnderecoDepartamento());
		to.setNmDepartamento(d.getNmDepartamento());
		to.setNrTelefoneDepartamento(d.getNrTelefoneDepartamento());

		to.setUnidade(unidadeBuilder.buildTO(d.getUnidade()));

		to.setDepartamentoSuperior(buildTODepartamentoSuperior(d.getDepartamentosSuperior()));

		return to;
	}

	private DepartamentoTO buildTODepartamentoSuperior(Departamentos dto) {
		DepartamentoTO to = new DepartamentoTO();
		
		if (Objects.isNull(dto) || Objects.isNull(dto.getIdDepartamento())) {
			return to;
		}
		
		to.setIdDepartamento(dto.getIdDepartamento());
		to.setCdUnidadeDepartamento(dto.getCdUnidadeDepartamento());
		to.setDsEnderecoDepartamento(dto.getDsEnderecoDepartamento());
		to.setNmDepartamento(dto.getNmDepartamento());
		to.setNrTelefoneDepartamento(dto.getNrTelefoneDepartamento());
		to.setUnidade(unidadeBuilder.buildTO(dto.getUnidade()));
		return to;
	}

	public List<DepartamentoTO> buildAll(List<Departamentos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
