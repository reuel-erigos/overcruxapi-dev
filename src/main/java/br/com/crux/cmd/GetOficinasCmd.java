package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OficinasTOBuilder;
import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.entity.Oficinas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.OficinasTO;

@Component
public class GetOficinasCmd {

	@Autowired private OficinasRepository repository;
	@Autowired private OficinasTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<OficinasTO> getAllVigentesAndPassadas() {
		List<OficinasTO> atividadesTO = getAllUnidadeLogada();
		List<OficinasTO> resultado = atividadesTO.stream()
				                                   .filter(r -> Objects.nonNull(r.getDataInicio()))
				                                   .filter( r -> {
											return Java8DateUtil.isVigente( r.getDataInicio().toLocalDate(), (Objects.nonNull(r.getDataFim()) ? r.getDataFim().toLocalDate() : null) )
												   ||
												   Objects.nonNull(r.getDataFim()) && r.getDataFim().toLocalDate().isBefore(LocalDate.now());
										}).collect(Collectors.toList());
		return resultado;
	}
	
	public List<OficinasTO> getAllVigentesAndFuturas() {
		List<OficinasTO> atividadesTO = getAllUnidadeLogada();
		List<OficinasTO> resultado = atividadesTO.stream()
				                                   .filter(r -> Objects.nonNull(r.getDataInicio()))
				                                   .filter( r -> {
											return Java8DateUtil.isVigente( r.getDataInicio().toLocalDate(), (Objects.nonNull(r.getDataFim()) ? r.getDataFim().toLocalDate() : null) )
												   ||
												   Objects.nonNull(r.getDataFim()) && r.getDataFim().toLocalDate().isAfter(LocalDate.now());
										}).collect(Collectors.toList());
		return resultado;
	}
	
	

	
	
	public List<OficinasTO> getAllUnidadeLogada() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Oficinas>> entitys = repository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<OficinasTO>();
	}
	
	
	public List<OficinasTO> getTOByIdTurma(Long idTurma) {
		Long idPresente = Optional.ofNullable(idTurma).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		
		Optional<List<Oficinas>> entitys = repository.findByIdTurma(idPresente);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<OficinasTO>();
	}
	
	
	public OficinasTO getTOById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		Oficinas entity = repository.findById(idPresente).orElseThrow(()-> new NotFoundException("Atividade não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Oficinas getById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		return repository.findById(idPresente).orElseThrow(()-> new NotFoundException("Atividade não encontrada."));
	}
			
}
