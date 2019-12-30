package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.QuestionariosTOBuilder;
import br.com.crux.dao.repository.QuestionariosRepository;
import br.com.crux.entity.Questionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.QuestionarioTO;

@Component
public class GetQuestionariosCmd {

	@Autowired private QuestionariosRepository repository;
	@Autowired private QuestionariosTOBuilder toBuilder;

	public List<QuestionarioTO> getAll() {
		List<QuestionarioTO> entitys = toBuilder.buildAll(repository.findAll());
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<QuestionarioTO>();
		}
		return entitys;
	}

	public QuestionarioTO getTOById(Long id) {
		Questionario entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Questionario não encontrado"));
		return toBuilder.buildTO(entity);
	}

	public Questionario getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
