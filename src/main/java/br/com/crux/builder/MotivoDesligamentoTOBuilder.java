package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class MotivoDesligamentoTOBuilder extends Builder<MotivoDesligamento, MotivoDesligamentoTO>{

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;

	public MotivoDesligamentoTO buildTO(MotivoDesligamento entity) {
		MotivoDesligamentoTO to = new MotivoDesligamentoTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		if(entity.getInstituicao() != null) {
			to.setIdInstituicao(entity.getInstituicao().getId());
		}
		
		return to;
	}


	public MotivoDesligamento build(MotivoDesligamentoTO to) {
		MotivoDesligamento entity = new MotivoDesligamento();

		BeanUtils.copyProperties(to, entity);
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		entity.setInstituicao(getInstituicaoCmd.getById(idInstituicao));
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
