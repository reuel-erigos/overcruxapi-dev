package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.crux.cmd.GetUsuarioSistemaCmd;
import br.com.crux.entity.UsuariosSistema;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetOficinasCmd;
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
	
	
	public GrupoAcoes build(GrupoAcoesTO p) {
		GrupoAcoes retorno = new GrupoAcoes();

		BeanUtils.copyProperties(p, retorno);
		
		Optional.ofNullable(p.getUsuarioAnalise()).ifPresent(usu -> {
			if(Objects.nonNull(usu.getId())) {
				UsuariosSistema usuario = getUsuarioSistemaCmd.getById(usu.getId());
				retorno.setUsuarioAnalise(usuario);
			}
		});

		Optional.ofNullable(p.getAtividade()).ifPresent(atv -> {
			Oficinas atividade = getAtividadeCmd.getById(atv.getId());
			retorno.setAtividade(atividade);
		});
		
		Optional.ofNullable(p.getAcoes()).ifPresent(acaoTO -> {
			retorno.setAcoes(acaoTOBuilder.buildTOAll(acaoTO));
		});

		return retorno;
	}

	public GrupoAcoesTO buildTO(GrupoAcoes p) {
		GrupoAcoesTO retorno = new GrupoAcoesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setUsuarioAnalise(usuariosSistemaTOBuilder.buildTO(p.getUsuarioAnalise()));
		
		if(Objects.nonNull(p.getId())) {
			if(!p.getAcoes().isEmpty()) {
				retorno.setAcoes(acaoTOBuilder.buildAll(p.getAcoes()));
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
