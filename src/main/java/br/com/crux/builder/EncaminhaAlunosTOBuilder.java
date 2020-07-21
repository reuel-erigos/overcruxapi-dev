package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.to.EncaminhaAlunosTO;

@Component
public class EncaminhaAlunosTOBuilder {

	@Autowired private EntidadesSociaisTOBuilder entidadesSociaisBuilder;

	public EncaminhaAlunos build(EncaminhaAlunosTO p) {
		EncaminhaAlunos retorno = new EncaminhaAlunos();

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setEntidadesSocial(entidadesSociaisBuilder.build(p.getEntidadeSocial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public EncaminhaAlunosTO buildTO(EncaminhaAlunos p) {
		EncaminhaAlunosTO retorno = new EncaminhaAlunosTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		retorno.setEntidadeSocial(entidadesSociaisBuilder.buildTO(p.getEntidadesSocial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());


		return retorno;
	}

	public List<EncaminhaAlunosTO> buildAllTO(List<EncaminhaAlunos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
