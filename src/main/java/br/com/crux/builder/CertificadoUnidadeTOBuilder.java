package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CertificadoUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.CertificadoUnidadeTO;

@Component
public class CertificadoUnidadeTOBuilder {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public CertificadoUnidadeTO buildTO(CertificadoUnidade entity) {
		CertificadoUnidadeTO to = new CertificadoUnidadeTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);

		return to;
	}

	public List<CertificadoUnidadeTO> buildAll(List<CertificadoUnidade> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public CertificadoUnidade build(CertificadoUnidadeTO to, Unidade unidade) {
		CertificadoUnidade entity = new CertificadoUnidade();

		BeanUtils.copyProperties(to, entity);

		entity.setUnidade(unidade);
		entity.setUsuarioAlteracao(
				getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
