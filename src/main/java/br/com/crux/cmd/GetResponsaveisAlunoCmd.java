package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ResponsaveisAlunoTOBuilder;
import br.com.crux.dao.repository.ResponsaveisAlunoRepository;
import br.com.crux.entity.ResponsaveisAluno;
import br.com.crux.to.ResponsaveisAlunoTO;

@Component
public class GetResponsaveisAlunoCmd {

	@Autowired private ResponsaveisAlunoRepository repository;
	@Autowired private ResponsaveisAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<ResponsaveisAlunoTO> getAllByFamiliar(Long idFamiliar) {
		List<ResponsaveisAlunoTO> retorno = new ArrayList<ResponsaveisAlunoTO>();
		
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		
		Optional<List<ResponsaveisAluno>> responsaveis = repository.findByUnidadeAndFamiliar(idUnidade, idFamiliar);
		if(!responsaveis.isPresent()) {return new ArrayList<ResponsaveisAlunoTO>();}
		
		responsaveis.get().stream().forEach(r -> retorno.add(toBuilder.buildTO(r)));
		return retorno;
	}

			
}
