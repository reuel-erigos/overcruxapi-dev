package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetOficinasCmd;
import br.com.crux.entity.CadastroReservaAtividade;
import br.com.crux.entity.Oficinas;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class CadastroReservaAtividadeTOBuilder {

	@Autowired private OficinasTOBuilder atividadeBuilder;
	@Autowired private GetOficinasCmd getAtividadeCmd;

	
	public CadastroReservaAtividade build(CadastroReservaAtividadeTO p) {
		CadastroReservaAtividade retorno = new CadastroReservaAtividade();

		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		Optional.ofNullable(p.getAtividade()).ifPresent(atividade -> {
			Oficinas atv = getAtividadeCmd.getById(atividade.getId());
			retorno.setAtividade(atv);
		});
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public CadastroReservaAtividadeTO buildTO(CadastroReservaAtividade p) {
		CadastroReservaAtividadeTO retorno = new CadastroReservaAtividadeTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<CadastroReservaAtividadeTO> buildAll(List<CadastroReservaAtividade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
