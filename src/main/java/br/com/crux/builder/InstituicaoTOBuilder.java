package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Instituicao;
import br.com.crux.to.InstituicaoTO;

@Component
public class InstituicaoTOBuilder {

	public Instituicao build(InstituicaoTO to) {

		Instituicao retorno = new Instituicao();
		BeanUtils.copyProperties(to, retorno);
		
		return retorno;
	}

	public InstituicaoTO buildTO(Instituicao instituicao) {
		InstituicaoTO to = new InstituicaoTO();

		if (Objects.isNull(instituicao)) {
			return to;
		}

		BeanUtils.copyProperties(instituicao, to);
		
		return to;
	}

	public List<InstituicaoTO> buildAllTO(List<Instituicao> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	

}
