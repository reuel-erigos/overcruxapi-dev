package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.InstituicaoTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.builder.UsuariosSistemaTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.UnidadeJaExisteException;
import br.com.crux.rule.ValidarCadastroUnidadeRule;
import br.com.crux.to.UnidadeTO;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class CadastrarUnidadeCmd {

	
	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ValidarCadastroUnidadeRule validarCadastroUnidadeRule;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	
	@Autowired private CadastrarInstituicaoCmd cadastrarInstituicaoCmd;
	@Autowired private InstituicaoTOBuilder instituicaoTOBuilder;
	@Autowired private CadastrarUsuariosUnidadeCmd cadastrarUsuariosUnidadeCmd;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;
	@Autowired private UsuariosSistemaTOBuilder usuariosSistemaTOBuilder;
	
	

	public UnidadeTO cadastrar(UnidadeTO to) {
		validarCadastroUnidadeRule.validar(to.getSiglaUnidade(), to.getNomeUnidade());
		
		Optional<Unidade> siglaUnidade = unidadeRepository.findBySiglaUnidadeIgnoreCase(to.getSiglaUnidade());
		if (siglaUnidade.isPresent()) {
			throw new UnidadeJaExisteException("Já existe unidade com essa sigla.");
		}

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Unidade unidade = unidadeTOBuilder.build(to);

		if(Objects.nonNull(unidade.getInstituicao())) {
			unidade.setInstituicao( instituicaoTOBuilder.build(cadastrarInstituicaoCmd.cadastrar(to.getInstituicao())));
		}
		
		Unidade retorno = unidadeRepository.save(unidade);
		
		UsuariosUnidadesTO usuariosUnidadesTO = new UsuariosUnidadesTO();
		usuariosUnidadesTO.setUnidade(unidadeTOBuilder.buildTO(retorno));
		usuariosUnidadesTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		UsuariosSistema usuarioSistema = getUsuarioSistemaCmd.getById(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		usuariosUnidadesTO.setUsuarioSistema(usuariosSistemaTOBuilder.buildTO(usuarioSistema));

		cadastrarUsuariosUnidadeCmd.cadastrar(usuariosUnidadesTO, usuarioSistema);
		
		return unidadeTOBuilder.buildTO(retorno);
	}
}
