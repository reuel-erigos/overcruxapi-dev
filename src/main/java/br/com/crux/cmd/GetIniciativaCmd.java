package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IniciativaTOBuilder;
import br.com.crux.dao.repository.IniciativaRepository;
import br.com.crux.entity.Iniciativa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.IniciativaTO;

@Component
public class GetIniciativaCmd {

	@Autowired private IniciativaRepository iniciativaRepository;
	@Autowired private IniciativaTOBuilder iniciativaTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<IniciativaTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Iniciativa>> listaRetorno = iniciativaRepository.findByIdUnidade(idUnidade);
		if(listaRetorno.isPresent()) {
			return iniciativaTOBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<IniciativaTO>();
	}
	
	public IniciativaTO getTOById(Long id) {
		Iniciativa entity = iniciativaRepository.findById(id).orElseThrow(() -> new NotFoundException("Iniciativa não encontrado"));
		return iniciativaTOBuilder.buildTO(entity);
	}

	public Iniciativa getById(Long id) {
		return iniciativaRepository.findById(id).orElseGet(null);
	}
			
}
