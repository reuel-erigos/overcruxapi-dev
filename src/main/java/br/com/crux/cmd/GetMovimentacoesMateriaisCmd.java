package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesMateriaisRepository;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class GetMovimentacoesMateriaisCmd {

	@Autowired private MovimentacoesMateriaisRepository repository;
	@Autowired private MovimentacoesMateriaisTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<MovimentacoesMateriaisTO> getAll() {
	
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<MovimentacoesMateriais>> entitys = repository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<MovimentacoesMateriaisTO>();
		
	}
	
	public MovimentacoesMateriaisTO getTOById(Long id) {
		MovimentacoesMateriais entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(entity);
	}
	
	public MovimentacoesMateriais getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
