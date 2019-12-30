package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.InstituicaoTOBuilder;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.ValidarCadastroInstituicaoRule;
import br.com.crux.to.InstituicaoTO;

@Component
public class AlterarInstituicaoCmd {

	@Autowired private ValidarCadastroInstituicaoRule validarCadastroRule;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private InstituicaoTOBuilder toBuilder;
	@Autowired private InstituicaoRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public InstituicaoTO alterar(InstituicaoTO to) {
		validarCadastroRule.validar(to);

		Instituicao instituicao = Optional.ofNullable(getInstituicaoCmd.getById(to.getId())).orElseThrow(() -> new NotFoundException("Instituição não encontrada."));
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		instituicao = toBuilder.build(to);
		
		Instituicao retorno = repository.save(instituicao);
		return toBuilder.buildTO(retorno);

	}
}
