package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramaProjetoInstituicaoTOBuilder;
import br.com.crux.dao.repository.VWProgramasProjetosInstituicaoRepository;
import br.com.crux.entity.view.ProgramasProjetosInstituicao;
import br.com.crux.to.ProgramaProjetoInstituicaoTO;

@Component
public class GetProgramaProjetoInsticuicaoCmd {
	
	@Autowired private VWProgramasProjetosInstituicaoRepository repository;
	@Autowired private ProgramaProjetoInstituicaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<ProgramaProjetoInstituicaoTO> getAll(){
		Optional<List<ProgramasProjetosInstituicao>> lista = repository.getAllPorIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		if(lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		return null;
	}

	
}
