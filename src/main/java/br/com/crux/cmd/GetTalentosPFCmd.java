package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TalentosPFTOBuilder;
import br.com.crux.dao.repository.TalentosPFRepository;
import br.com.crux.entity.TalentosPf;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.TalentosPfTO;

@Component
public class GetTalentosPFCmd {

	@Autowired private TalentosPFRepository repository;
	@Autowired private TalentosPFTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<TalentosPfTO> getAllPorUnidadeLogada() {
		Long idUnidadeLogada = getUnidadeLogadaCmd.get().getId();
		
		Optional<List<TalentosPf>> talentos = repository.findAllByUnidade(idUnidadeLogada);
		
		if(talentos.isPresent()) {
			return toBuilder.buildAll(talentos.get());
		} 

		return new ArrayList<TalentosPfTO>();
	}

	public TalentosPfTO getTOById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		TalentosPf entity = repository.findById(idPresente).orElseThrow(() -> new NotFoundException("Talentos do Funcionario não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public TalentosPf getById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		return repository.findById(idPresente).orElseGet(null);
	}

	public List<TalentosPfTO> getByIdPessoaFisica(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		List<TalentosPf> lista = repository.getByIdPessoaFisica(idPresente).orElseThrow(() -> new NotFoundException("Talentos do Funcionario não encontrado."));
		return toBuilder.buildAll(lista);
	}

}
