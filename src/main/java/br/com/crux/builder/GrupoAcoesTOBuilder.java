package br.com.crux.builder;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.crux.cmd.*;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.to.AcaoTO;
import br.com.crux.to.UsuariosSistemaTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.GrupoAcoes;
import br.com.crux.entity.Oficinas;
import br.com.crux.to.GrupoAcoesSimlesTO;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class GrupoAcoesTOBuilder {

	@Autowired private OficinasTOBuilder atividadeBuilder;
	@Autowired private UsuariosSistemaTOBuilder usuariosSistemaTOBuilder;
	@Autowired private GetOficinasCmd getAtividadeCmd;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;
	@Autowired private AcaoTOBuilder acaoTOBuilder;
	@Autowired private GetGrupoAcoesCmd getGrupoAcoesCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;


	public GrupoAcoes build(GrupoAcoesTO p) {
		GrupoAcoes retorno = new GrupoAcoes();

		BeanUtils.copyProperties(p, retorno);

		String statusAtual = Optional.ofNullable(p.getId())
				.map(id -> getGrupoAcoesCmd.getById(id))
				.map(GrupoAcoes::getStatusAnalise)
				.orElse(null);

		String statusAlterado = p.getStatusAnalise();
		if (isStatusChanged(statusAtual, statusAlterado))
		{
			Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
			UsuariosSistema usuario = getUsuarioSistemaCmd.getById(idUsuarioLogado);
			retorno.setUsuarioAnalise(usuario);
		} else
		{
			Optional.ofNullable(p.getUsuarioAnalise())
					.map(UsuariosSistemaTO::getId)
					.ifPresent(id -> retorno.setUsuarioAnalise(usuariosSistemaTOBuilder.build(p.getUsuarioAnalise())));
		}

		Optional.ofNullable(p.getAtividade()).ifPresent(atv -> {
			Oficinas atividade = getAtividadeCmd.getById(atv.getId());
			retorno.setAtividade(atividade);
		});
		
		Optional.ofNullable(p.getAcoes()).ifPresent(acaoTO -> {
			retorno.setAcoes(acaoTOBuilder.buildTOAll(acaoTO));
		});

		return retorno;
	}

	private boolean isStatusChanged(String statusAtual, String statusAlterado)
	{
		return statusAlterado != null && (statusAtual == null || !statusAlterado.equals(statusAtual));
	}

	public GrupoAcoesTO buildTO(GrupoAcoes p) {
		GrupoAcoesTO retorno = new GrupoAcoesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));

		Optional.ofNullable(p.getUsuarioAnalise())
				.ifPresent(usuario -> retorno.setUsuarioAnalise(usuariosSistemaTOBuilder.buildTO(usuario)));

		if(Objects.nonNull(p.getId())) {
			if(!p.getAcoes().isEmpty()) {
				retorno.setAcoes(acaoTOBuilder.buildAll(p.getAcoes()));
				retorno.getAcoes().sort(Comparator.comparing(AcaoTO::getDataPrevisaoInicio));
			}
		}

		return retorno;
	}

	public GrupoAcoesTO buildSemAcao(GrupoAcoes p) {
		GrupoAcoesTO retorno = new GrupoAcoesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setUsuarioAnalise(usuariosSistemaTOBuilder.buildTO(p.getUsuarioAnalise()));

		return retorno;
	}

	

	public GrupoAcoesSimlesTO buildSimplesTO(GrupoAcoes p) {
		GrupoAcoesSimlesTO retorno = new GrupoAcoesSimlesTO();
		
		if (Objects.isNull(p)) {return retorno;}
		
		BeanUtils.copyProperties(p, retorno);
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setStatusAnalise(p.getStatusAnalise());
		retorno.setStatusEnvioAnalise(p.getStatusEnvioAnalise());

		return retorno;
	}
	
	public List<GrupoAcoesTO> buildAll(List<GrupoAcoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<GrupoAcoes> buildTOAll(List<GrupoAcoesTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
}
