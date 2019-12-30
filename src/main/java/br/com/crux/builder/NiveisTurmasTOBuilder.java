package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.NiveisTurmas;
import br.com.crux.to.NiveisTurmasTO;

@Component
public class NiveisTurmasTOBuilder {

	public NiveisTurmas build(NiveisTurmasTO to) {
		NiveisTurmas entity = new NiveisTurmas();
		
		BeanUtils.copyProperties(to, entity);
		entity.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return entity;
	}

	public NiveisTurmasTO buildTO(NiveisTurmas d) {
		NiveisTurmasTO to = new NiveisTurmasTO();

		if (Objects.isNull(d)) {
			return to;
		}
		
		BeanUtils.copyProperties(d, to);
		to.setUsuarioAlteracao(d.getUsuarioAlteracao());

		return to;
	}

	public List<NiveisTurmasTO> buildAll(List<NiveisTurmas> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
