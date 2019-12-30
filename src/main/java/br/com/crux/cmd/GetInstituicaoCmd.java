package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.InstituicaoTOBuilder;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.InstituicaoTO;

@Component
public class GetInstituicaoCmd {

	@Autowired private InstituicaoRepository instituicaoRepository;
	@Autowired private InstituicaoTOBuilder instituicaoBuilder;
	
	public List<InstituicaoTO> getAll() {
		List<Instituicao> instituicoes = instituicaoRepository.findAll();
		if(instituicoes == null || instituicoes.isEmpty()) {
			return new ArrayList<InstituicaoTO>();
		}
		return instituicaoBuilder.buildAllTO(instituicoes);
	}
	

	public Instituicao getById(Long id) {
		return instituicaoRepository.findById(id).orElse(null);
	}
	
	public InstituicaoTO getTOById(Long id) {
		Instituicao instituicao = instituicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Instituicao não encontrada"));
		return instituicaoBuilder.buildTO(instituicao);
	}
	
	
}
