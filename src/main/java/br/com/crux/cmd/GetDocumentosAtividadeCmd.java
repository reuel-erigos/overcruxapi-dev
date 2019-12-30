package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DocumentosAtividadeTOBuilder;
import br.com.crux.dao.repository.DocumentosAtividadeRepository;
import br.com.crux.entity.DocumentosAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.DocumentosAtividadeTO;

@Component
public class GetDocumentosAtividadeCmd {

	@Autowired private DocumentosAtividadeRepository repository;
	@Autowired private DocumentosAtividadeTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<DocumentosAtividadeTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		
		Optional<List<DocumentosAtividade>> listaRetorno = repository.findByUnidade(idUnidade);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<DocumentosAtividadeTO>();
	}
	
	public DocumentosAtividadeTO getTOById(Long id) {
		DocumentosAtividade entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Documento da Atividade não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public List<DocumentosAtividadeTO> getPorAtividade(Long id) {
		List<DocumentosAtividade> lista = repository.getPorAtividade(id).orElseThrow(() -> new NotFoundException("Documento da Atividade não encontrado."));
		return toBuilder.buildAll(lista);
	}
			
}
