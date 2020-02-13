package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesInstituicaoTOBuilder;
import br.com.crux.builder.ProjetosUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.FuncoesInstituicaoRepository;
import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class CadastrarListaFuncoesInstituicaoCmd {

	@Autowired FuncoesInstituicaoRepository funcoesInstituicaoRepository;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired FuncoesInstituicaoTOBuilder funcoesInstituicaoTOBuilder;
	@Autowired ProjetosUnidadeTOBuilder projetosUnidadeTOBuilder;

	public FuncoesInstituicao cadastrar(FuncoesInstituicao funcoesInstituicao) {
		return funcoesInstituicaoRepository.save(funcoesInstituicao);
	}

	public List<FuncoesInstituicao> cadastrarLista(Instituicao instituicao, List<FuncoesInstituicaoTO> list) {
		return list.stream()
				.map(fi -> funcoesInstituicaoTOBuilder.build(instituicao, fi))
				.map(this::cadastrar)
				.collect(Collectors.toList());

	}

}
