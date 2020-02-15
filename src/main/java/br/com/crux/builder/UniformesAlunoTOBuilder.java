package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.UniformesAluno;
import br.com.crux.to.UniformesAlunoTO;

@Component
public class UniformesAlunoTOBuilder {

	@Autowired
	private AtividadesAlunoTOBuilder atividadesAlunoBuilder;

	public UniformesAluno build(UniformesAlunoTO p) {
		UniformesAluno retorno = new UniformesAluno();

		retorno.setId(p.getId());
		retorno.setNomeUniforme(p.getNomeUniforme());
		retorno.setDataUniformeEntregue(p.getDataUniformeEntregue());
		retorno.setQtdUniformeEntregue(p.getQtdUniformeEntregue());
		retorno.setAtividadesAluno(atividadesAlunoBuilder.build(p.getAtividadesAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public UniformesAlunoTO buildTO(UniformesAluno p) {
		UniformesAlunoTO retorno = new UniformesAlunoTO();
		
		retorno.setId(p.getId());
		retorno.setNomeUniforme(p.getNomeUniforme());
		retorno.setDataUniformeEntregue(p.getDataUniformeEntregue());
		retorno.setQtdUniformeEntregue(p.getQtdUniformeEntregue());
		retorno.setAtividadesAluno(atividadesAlunoBuilder.buildTO(p.getAtividadesAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<UniformesAlunoTO> buildAll(List<UniformesAluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
