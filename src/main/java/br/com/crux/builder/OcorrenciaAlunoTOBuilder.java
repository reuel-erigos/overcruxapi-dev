package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.OcorrenciaAluno;
import br.com.crux.to.OcorrenciaAlunoTO;

@Component
public class OcorrenciaAlunoTOBuilder {

	@Autowired private AlunoTOBuilder alunoTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private TipoOcorrenciaAlunoTOBuilder tipoOcorrenciaAlunoTOBuilder;
	

	public OcorrenciaAluno build(OcorrenciaAlunoTO param) {
		OcorrenciaAluno retorno = new OcorrenciaAluno();

		retorno.setId(param.getId());
		retorno.setAluno(alunoTOBuilder.build(param.getAluno()));
		retorno.setFuncionario(funcionarioTOBuilder.build(param.getFuncionario()));
		retorno.setTipoOcorrenciaAluno(tipoOcorrenciaAlunoTOBuilder.build(param.getTipoOcorrenciaAluno()));
		retorno.setDescricao(param.getDescricao());
		retorno.setLocal(param.getLocal());
		retorno.setDataOcorrencia(param.getDataOcorrencia());
		retorno.setDataCiencia(param.getDataCiencia());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		return retorno;
	}

	public OcorrenciaAlunoTO buildTO(OcorrenciaAluno param) {
		OcorrenciaAlunoTO retorno = new OcorrenciaAlunoTO();
		
		if(Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setAluno(alunoTOBuilder.buildTO(param.getAluno()));
		retorno.setFuncionario(funcionarioTOBuilder.buildTO(param.getFuncionario()));
		retorno.setTipoOcorrenciaAluno(tipoOcorrenciaAlunoTOBuilder.buildTO(param.getTipoOcorrenciaAluno()));
		retorno.setDescricao(param.getDescricao());
		retorno.setLocal(param.getLocal());
		retorno.setDataOcorrencia(param.getDataOcorrencia());
		retorno.setDataCiencia(param.getDataCiencia());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());


		return retorno;
	}

	public List<OcorrenciaAlunoTO> buildAll(List<OcorrenciaAluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
