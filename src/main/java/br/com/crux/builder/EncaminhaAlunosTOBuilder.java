package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.to.EncaminhaAlunosTO;

@Component
public class EncaminhaAlunosTOBuilder {

	@Autowired private EntidadesSociaisTOBuilder entidadesSociaisBuilder;
	@Autowired private AlunoTOBuilder alunoBuilder;

	public EncaminhaAlunos build(EncaminhaAlunosTO p) {
		EncaminhaAlunos retorno = new EncaminhaAlunos();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataEncaminhaAluno(p.getDataEncaminhaAluno());
		retorno.setAluno(alunoBuilder.build(p.getAluno()));
		retorno.setEntidadesSocial(entidadesSociaisBuilder.build(p.getEntidadeSocial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public EncaminhaAlunosTO buildTO(EncaminhaAlunos p) {
		EncaminhaAlunosTO retorno = new EncaminhaAlunosTO();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataEncaminhaAluno(p.getDataEncaminhaAluno());
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setEntidadeSocial(entidadesSociaisBuilder.buildTO(p.getEntidadesSocial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());


		return retorno;
	}

	public List<EncaminhaAlunosTO> buildAll(List<EncaminhaAlunos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
