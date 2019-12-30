package br.com.crux.builder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.FrequenciasAlunos;
import br.com.crux.to.FrequenciasAlunosTO;

@Component
public class FrequenciasAlunosTOBuilder {

	@Autowired
	private AtividadesAlunoTOBuilder atividadesAlunoBuilder;

	public FrequenciasAlunos build(FrequenciasAlunosTO p) {
		FrequenciasAlunos retorno = new FrequenciasAlunos();

		retorno.setId(p.getId());
		retorno.setDataFrequencia(p.getDataFrequencia().toLocalDate());
		retorno.setJustificativa(p.getJustificativa());
		retorno.setFrequencia(p.getFrequencia());
		retorno.setAtividadesAluno(atividadesAlunoBuilder.build(p.getAtividadesAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public FrequenciasAlunosTO buildTO(FrequenciasAlunos p) {
		FrequenciasAlunosTO retorno = new FrequenciasAlunosTO();
		
		retorno.setId(p.getId());
		retorno.setDataFrequencia(LocalDateTime.of(p.getDataFrequencia(), LocalTime.now()));
		retorno.setFrequencia(p.getFrequencia());
		retorno.setJustificativa(p.getJustificativa());
		retorno.setAtividadesAluno(atividadesAlunoBuilder.buildTO(p.getAtividadesAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());


		return retorno;
	}

	public List<FrequenciasAlunosTO> buildAll(List<FrequenciasAlunos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
