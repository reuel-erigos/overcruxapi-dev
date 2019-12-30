package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ReprovacoesAlunoTOBuilder;
import br.com.crux.dao.repository.ReprovacoesAlunoRepository;
import br.com.crux.entity.ReprovacoesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ReprovacoesAlunoTO;

@Component
public class GetReprovacoesAlunoCmd {

	@Autowired private ReprovacoesAlunoRepository repository;
	@Autowired private ReprovacoesAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<ReprovacoesAlunoTO> getAll() {
		Optional<List<ReprovacoesAluno>> reprovacoes = repository.findByUnidade(getUnidadeLogadaCmd.get().getId());
		if(!reprovacoes.isPresent()) {
			return new ArrayList<ReprovacoesAlunoTO>();
		}
		return toBuilder.buildAll(reprovacoes.get());
	}
	
	public ReprovacoesAlunoTO getById(Long id) {
		ReprovacoesAluno entityOptional = repository.findById(id).orElseThrow(() -> new NotFoundException("Reprovacoes do Aluno não encontrada.") );
		return toBuilder.buildTO(entityOptional);
	}
			
}
