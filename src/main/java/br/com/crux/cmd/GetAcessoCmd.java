package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcessoTOBuilder;
import br.com.crux.builder.PerfilAcessoUsuarioTOBuilder;
import br.com.crux.dao.AcessoDao;
import br.com.crux.dao.repository.ModuloRepository;
import br.com.crux.dao.repository.PerfilAcessoRepository;
import br.com.crux.entity.Modulo;
import br.com.crux.entity.PerfilAcesso;
import br.com.crux.exception.ModuloNaoEncontradoException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.AcessoTO;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.PerfilAcessoUsuarioTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class GetAcessoCmd {

	@Autowired private PerfilAcessoRepository perfilAcessoRepository;
	@Autowired private ModuloRepository moduloRepository;
	@Autowired private AcessoDao acessoDao;
	@Autowired private AcessoTOBuilder acessoTOBuilder;
	@Autowired private PerfilAcessoUsuarioTOBuilder perfilAcessoUsuarioTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<PerfilAcesso> getAllPerfilAcesso() {
		List<PerfilAcesso> entitys = perfilAcessoRepository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<PerfilAcesso>();
		}
		return entitys;
	}

	public List<PerfilAcessoUsuarioTO> getPerfilAcessoDoUsuario(Long idUnidade, Long idUsuario, Long idModulo) {
		if (Objects.isNull(idUnidade)) {
			throw new ParametroNaoInformadoException("Erro ao recuperar os perfils do usuário, unidade não informada.");
		}
		return perfilAcessoUsuarioTOBuilder.buildAll(acessoDao.getPerfilAcessoDoUsuario(idUnidade, idUsuario, idModulo));
	}

	public List<PerfilAcessoUsuarioTO> getPerfilAcessoDoUsuarioNaUnidadeLogada(Long idUsuario, Long idModulo) {
		return perfilAcessoUsuarioTOBuilder.buildAll(acessoDao.getPerfilAcessoDoUsuario(getUnidadeLogadaCmd.get().getId(), idUsuario, idModulo));
	}

	public List<AcessoTO> getPerfilAcesso(String nomeModulo) {
		Optional.ofNullable(nomeModulo).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao recuperar os perfils do usuário, unidade não informada."));

		Modulo modulo = moduloRepository.findByNome(nomeModulo).orElseThrow(() -> new ModuloNaoEncontradoException("Módulo "+nomeModulo+" não encontrado"));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();

		AcessoUnidadeTO acessoUnidadeTO = getUnidadeLogadaCmd.get();

		return acessoTOBuilder.buildAll(acessoDao.getPerfilAcesso(acessoUnidadeTO.getId(), usuarioLogado.getIdUsuario(), modulo.getId()));
	}


}
