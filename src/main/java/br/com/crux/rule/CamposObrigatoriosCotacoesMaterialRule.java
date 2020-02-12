package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CotacoesMaterialTO;

@Component
public class CamposObrigatoriosCotacoesMaterialRule {

	public void verificar(CotacoesMaterialTO to) {

		if (Objects.isNull(to.getMaterial()) || Objects.isNull(to.getMaterial()
				.getId())) {
			throw new CamposObrigatoriosException("O material deve ser informado.");
		}

		if (Objects.isNull(to.getPedido()) || Objects.isNull(to.getPedido()
				.getId())) {
			throw new CamposObrigatoriosException("O pedido deve ser informado.");
		}

		if (Objects.isNull(to.getEmpresa()) || Objects.isNull(to.getEmpresa()
				.getId())) {
			throw new CamposObrigatoriosException("A empresa deve ser informada.");
		}

		if (Objects.isNull(to.getDataCotacao())) {
			throw new CamposObrigatoriosException("A data da cotação deve ser informada.");
		}

	}
}
