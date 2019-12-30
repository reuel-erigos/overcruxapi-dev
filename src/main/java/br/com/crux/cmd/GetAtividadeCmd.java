package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesTOBuilder;
import br.com.crux.dao.repository.AtividadeRepository;
import br.com.crux.entity.Atividades;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AtividadesTO;

@Component
public class GetAtividadeCmd {

	@Autowired private AtividadeRepository repository;
	@Autowired private AtividadesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<AtividadesTO> getAllVigentesAndPassadas() {
		List<AtividadesTO> atividadesTO = getAllUnidadeLogada();
		List<AtividadesTO> resultado = atividadesTO.stream()
				                                   .filter(r -> Objects.nonNull(r.getDataInicio()))
				                                   .filter( r -> {
											return Java8DateUtil.isVigente( r.getDataInicio().toLocalDate(), (Objects.nonNull(r.getDataFim()) ? r.getDataFim().toLocalDate() : null) )
												   ||
												   Objects.nonNull(r.getDataFim()) && r.getDataFim().toLocalDate().isBefore(LocalDate.now());
										}).collect(Collectors.toList());
		return resultado;
	}
	
	public List<AtividadesTO> getAllVigentesAndFuturas() {
		List<AtividadesTO> atividadesTO = getAllUnidadeLogada();
		List<AtividadesTO> resultado = atividadesTO.stream()
				                                   .filter(r -> Objects.nonNull(r.getDataInicio()))
				                                   .filter( r -> {
											return Java8DateUtil.isVigente( r.getDataInicio().toLocalDate(), (Objects.nonNull(r.getDataFim()) ? r.getDataFim().toLocalDate() : null) )
												   ||
												   Objects.nonNull(r.getDataFim()) && r.getDataFim().toLocalDate().isAfter(LocalDate.now());
										}).collect(Collectors.toList());
		return resultado;
	}
	
	

	
	
	public List<AtividadesTO> getAllUnidadeLogada() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Atividades>> entitys = repository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AtividadesTO>();
	}
	
	
	public List<AtividadesTO> getTOByIdTurma(Long idTurma) {
		Long idPresente = Optional.ofNullable(idTurma).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		
		Optional<List<Atividades>> entitys = repository.findByIdTurma(idPresente);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<AtividadesTO>();
	}
	
	
	public AtividadesTO getTOById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		Atividades entity = repository.findById(idPresente).orElseThrow(()-> new NotFoundException("Atividade não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Atividades getById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		return repository.findById(idPresente).orElseThrow(()-> new NotFoundException("Atividade não encontrada."));
	}
			
}
