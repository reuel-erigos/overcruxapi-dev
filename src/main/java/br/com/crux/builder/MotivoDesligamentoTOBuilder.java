package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class MotivoDesligamentoTOBuilder extends Builder<MotivoDesligamento, MotivoDesligamentoTO>{

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public MotivoDesligamentoTO buildTO(MotivoDesligamento entity) {
		MotivoDesligamentoTO to = new MotivoDesligamentoTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		
		return to;
	}


	public MotivoDesligamento build(MotivoDesligamentoTO to) {
		MotivoDesligamento entity = new MotivoDesligamento();

		BeanUtils.copyProperties(to, entity);
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
