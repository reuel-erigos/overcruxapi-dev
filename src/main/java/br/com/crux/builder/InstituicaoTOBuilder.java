package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncoesInstituicaoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Instituicao;
import br.com.crux.to.InstituicaoTO;

@Component
public class InstituicaoTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetFuncoesInstituicaoCmd getFuncoesInstituicaoCmd;

	public Instituicao build(InstituicaoTO to) {

		Instituicao retorno = new Instituicao();
		BeanUtils.copyProperties(to, retorno);

		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());
		return retorno;
	}

	public InstituicaoTO buildTO(Instituicao instituicao) {
		InstituicaoTO to = new InstituicaoTO();

		if (Objects.isNull(instituicao)) {
			return to;
		}

		BeanUtils.copyProperties(instituicao, to);
		
		to.setFuncoesInstituicao(getFuncoesInstituicaoCmd.getFuncoesInstituicaoTOByInstituicao(instituicao));

		return to;
	}

	public List<InstituicaoTO> buildAllTO(List<Instituicao> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

}
