package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.InstituicaoTOBuilder;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.InstituicaoTO;

@Component
public class GetInstituicaoCmd {

	@Autowired private InstituicaoRepository instituicaoRepository;
	@Autowired private InstituicaoTOBuilder instituicaoBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	public List<InstituicaoTO> getAll() {
		List<Instituicao> instituicoes = instituicaoRepository.findAll();
		if(instituicoes == null || instituicoes.isEmpty()) {
			return new ArrayList<InstituicaoTO>();
		}
		return instituicaoBuilder.buildAllTO(instituicoes);
	}
	

	public List<InstituicaoTO> getInstituicoesComAcesso() {
		Optional<List<Instituicao>> instituicoes = instituicaoRepository.getInstituicoesComAcesso(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		if(!instituicoes.isPresent()) {
			return new ArrayList<InstituicaoTO>();
		}
		return instituicaoBuilder.buildAllTO(instituicoes.get());
	}	

	public Instituicao getById(Long id) {
		return instituicaoRepository.findById(id).orElse(null);
	}
	
	public InstituicaoTO getTOById(Long id) {
		Instituicao instituicao = instituicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Instituicao não encontrada"));
		return instituicaoBuilder.buildTO(instituicao);
	}


	public Instituicao getPorUnidade(Unidade unidade) {
		return instituicaoRepository.getPorUnidade(unidade.getIdUnidade()).orElseThrow(() -> new NotFoundException("Nenhuma entidade encotrada para Unidade"));
	}
	
	
}
