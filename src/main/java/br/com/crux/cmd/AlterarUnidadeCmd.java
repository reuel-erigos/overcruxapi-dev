package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.ValidarCadastroUnidadeRule;
import br.com.crux.to.UnidadeTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarUnidadeCmd {

	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ValidarCadastroUnidadeRule validarCadastroUnidadeRule;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private InstituicaoRepository instituicaoRepository;

	public UnidadeTO alterar(UnidadeTO to) {
		validarCadastroUnidadeRule.validar(to.getNomeUnidade(), to.getNomeUnidade());

		Unidade unidade = Optional.ofNullable(getUnidadeCmd.getById(to.getIdUnidade())).orElseThrow(() -> new NotFoundException("Unidade não encontrada."));

		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		to.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		unidade = unidadeTOBuilder.build(to);
		
		if(Objects.nonNull(unidade.getInstituicao())) {
			Optional<Instituicao> instituicao = instituicaoRepository.findById(unidade.getInstituicao().getId());
			if(instituicao.isPresent()) {
				unidade.setInstituicao(instituicao.get());
			}
		}

		Unidade retorno = unidadeRepository.save(unidade);
		return unidadeTOBuilder.buildTO(retorno);

	}
}
