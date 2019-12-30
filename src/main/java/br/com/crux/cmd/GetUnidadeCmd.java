package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcessoUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ClassificadorSituacaoImovel;
import br.com.crux.enums.TipoUnidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.SemAcessoUnidadeException;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.UnidadeTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class GetUnidadeCmd {
	

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private CarregarUnidadeLogadaCmd carregarUnidadeLogadaCmd;
	@Autowired private AcessoUnidadeTOBuilder unidadeTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<AcessoUnidadeTO> getUnidadesComAcesso() throws UsernameNotFoundException {
		Authentication authentication = getUsuarioLogadoCmd.getAuthentication();
		
		List<AcessoUnidadeTO> unidades = new ArrayList<>();
		authentication.getAuthorities().stream().forEach( autho -> {
			Optional<Unidade> unidade = unidadeRepository.findBySiglaUnidadeIgnoreCase(autho.getAuthority().replace("ROLE_", ""));
			
			if(unidade.isPresent()) {
				unidades.add(unidadeTOBuilder.build(unidade.get()));
			}
		});
		
		return unidades;
	}

	public Optional<UnidadeTO> getUnidadeUsuarioLogadoComAcesso(Long idUnidade) {
		UsuarioLogadoTO usuarioLogadoTO = getUsuarioLogadoCmd.getUsuarioLogado();
		
		Optional<Unidade> unidadeOptional = unidadeRepository.findUnidadeDoUsuarioLogado(usuarioLogadoTO.getIdUsuario(), idUnidade);
		if(!unidadeOptional.isPresent()) {
			throw new SemAcessoUnidadeException("Usuário não tem acesso a essa unidade.");
		}

		//Carrega a unidade logada no Contexto do usuário
		AcessoUnidadeTO unidadeLogada = carregarUnidadeLogadaCmd.carregarUnidadeLogada(unidadeOptional.get().getIdUnidade());
		usuarioLogadoTO.setUnidadeLogada(unidadeLogada);
		
		return Optional.ofNullable(unidadeBuilder.buildTO(unidadeOptional.get()));
	}
	
	
	
	public List<UnidadeTO> getAllUnidadesUsuarioLogadoTemAcesso() {
		UsuarioLogadoTO user = getUsuarioLogadoCmd.getUsuarioLogado();
		
		Optional<List<Unidade>> unidadesOptional = unidadeRepository.findAllUnidadesDoUsuarioLogado(user.getIdUsuario());
		if(!unidadesOptional.isPresent()) {
			return null;
		}
		
		return unidadeBuilder.buildAllTO(unidadesOptional.get());
	}

	
	public List<TipoUnidade> getAllTiposUnidade() {
		return Arrays.asList(TipoUnidade.values());
	}
	
	public List<ClassificadorSituacaoImovel> getAllClassificadorSituacaoImovel() {
		return Arrays.asList(ClassificadorSituacaoImovel.values());
	}
	

	public Unidade getBySigla(String sigla) {
		Optional<Unidade> unidade = unidadeRepository.findBySiglaUnidadeIgnoreCase(sigla);
		return unidade.orElse(null);
	}

	public Unidade getById(Long id) {
		return unidadeRepository.findById(id).orElse(null);
	}
	
	public UnidadeTO getTOById(Long id) {
		Unidade unidade = unidadeRepository.findById(id).orElseThrow(() -> new NotFoundException("Unidade não encontrada"));
		return unidadeBuilder.buildTO(unidade);
	}
	
	public List<UnidadeTO> getAllUnidadesByInstituicao(Long idInstituicao) {
		Optional<List<Unidade>> instituicoes = unidadeRepository.findAllInstituicao(idInstituicao);
		if(instituicoes.isPresent()) {
			return unidadeBuilder.buildAllTO(instituicoes.get());
		}
		return null;
	}
	
	public List<UnidadeTO> getAllUnidadesByInstituicaoLogada() {
		UnidadeTO unidadeLogada = getUnidadeLogadaCmd.getUnidadeTO();
		return getAllUnidadesByInstituicao(unidadeLogada.getInstituicao().getId());
	}
	
}
