package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CadastroReservaAtividadeTOBuilder;
import br.com.crux.dao.repository.CadastroReservaAtividadeRepository;
import br.com.crux.entity.CadastroReservaAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class GetCadastroReservaAtividadeCmd {

	@Autowired private CadastroReservaAtividadeRepository repository;
	@Autowired private CadastroReservaAtividadeTOBuilder toBuilder;

	public List<CadastroReservaAtividadeTO> getAll() {
		List<CadastroReservaAtividadeTO> entitys = toBuilder.buildAll(repository.findAll());
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<CadastroReservaAtividadeTO>();
		}
		return entitys;
	}

	public CadastroReservaAtividadeTO getById(Long id) {
		CadastroReservaAtividade entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Cadastro Reserva Atividade não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public List<CadastroReservaAtividadeTO> getPorAtividade(Long id) {
		List<CadastroReservaAtividade> entity = repository.getPorAtividade(id).orElseThrow(() -> new NotFoundException("Cadastro Reserva Atividade não encontrado."));
		return toBuilder.buildAll(entity);
	}

}
