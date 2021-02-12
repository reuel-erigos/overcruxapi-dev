package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesTOBuilder;
import br.com.crux.dao.repository.FuncoesRepository;
import br.com.crux.entity.Funcoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FuncoesTO;

@Component
public class GetFuncoesCmd {

	@Autowired private FuncoesRepository repository;
	@Autowired private FuncoesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<FuncoesTO> getAllByIdInstituicao(){
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Funcoes>> entitys = repository.getAllByIdInstituicao(idInstituicao);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<FuncoesTO>();
	}
	
	public FuncoesTO getTOById(Long id) {
		Funcoes funcoes = repository.findById(id).orElseThrow(() -> new NotFoundException("Função não encontrada."));
		return toBuilder.buildTO(funcoes);
	}
	
	public Funcoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
