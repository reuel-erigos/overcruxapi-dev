package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.TipoOcorrenciaAluno;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@Component
public class TipoOcorrenciaAlunoTOBuilder {


	public TipoOcorrenciaAluno build(TipoOcorrenciaAlunoTO param) {
		TipoOcorrenciaAluno retorno = new TipoOcorrenciaAluno();

		retorno.setId(param.getId());
		retorno.setInformaSAP(param.getInformaSAP());
		retorno.setTipoOcorrencia(param.getTipoOcorrencia());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		return retorno;
	}

	public TipoOcorrenciaAlunoTO buildTO(TipoOcorrenciaAluno param) {
		TipoOcorrenciaAlunoTO retorno = new TipoOcorrenciaAlunoTO();
		
		if(Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setInformaSAP(param.getInformaSAP());
		retorno.setTipoOcorrencia(param.getTipoOcorrencia());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		return retorno;
	}

	public List<TipoOcorrenciaAlunoTO> buildAll(List<TipoOcorrenciaAluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
