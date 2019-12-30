package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.ReprovacoesAluno;
import br.com.crux.to.ReprovacoesAlunoTO;

@Component
public class ReprovacoesAlunoTOBuilder {

	@Autowired private AlunoTOBuilder alunoBuilder;

	public ReprovacoesAluno build(ReprovacoesAlunoTO p) {
		ReprovacoesAluno retorno = new ReprovacoesAluno();

		retorno.setId(p.getId());
		retorno.setDataReprovacao(p.getDataReprovacao());
		retorno.setDescricaoMotivo(p.getDescricaoMotivo());
		retorno.setSerieReprovacao(p.getSerieReprovacao());
		retorno.setQtdReprovacao(p.getQtdReprovacao());
		retorno.setAluno(alunoBuilder.build(p.getAluno()));
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public ReprovacoesAlunoTO buildTO(ReprovacoesAluno p) {
		ReprovacoesAlunoTO retorno = new ReprovacoesAlunoTO();
		
		retorno.setId(p.getId());
		retorno.setDataReprovacao(p.getDataReprovacao());
		retorno.setDescricaoMotivo(p.getDescricaoMotivo());
		retorno.setSerieReprovacao(p.getSerieReprovacao());
		retorno.setQtdReprovacao(p.getQtdReprovacao());
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());


		return retorno;
	}

	public List<ReprovacoesAlunoTO> buildAll(List<ReprovacoesAluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
