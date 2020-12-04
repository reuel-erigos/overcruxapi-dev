package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.view.ProgramasProjetosInstituicao;
import br.com.crux.to.ProgramaProjetoInstituicaoTO;

@Component
public class ProgramaProjetoInstituicaoTOBuilder {
	
	public ProgramasProjetosInstituicao build(ProgramaProjetoInstituicaoTO param) {
		ProgramasProjetosInstituicao retorno = new ProgramasProjetosInstituicao();
		BeanUtils.copyProperties(param, retorno);
		return retorno;
	}

	public ProgramaProjetoInstituicaoTO buildTO(ProgramasProjetosInstituicao param) {
		ProgramaProjetoInstituicaoTO retorno = new ProgramaProjetoInstituicaoTO();
		if (Objects.isNull(param)) {
			return retorno;
		}
		BeanUtils.copyProperties(param, retorno);
		return retorno;
	}

	public List<ProgramaProjetoInstituicaoTO> buildAll(List<ProgramasProjetosInstituicao> dtos) {
		return dtos.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}
