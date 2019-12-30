package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.to.UnidadeTO;

@Component
public class SetUnidadeLogadaCmd {

	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public void set(List<UnidadeTO> listaUnidades) {

		Long idUnidadeLogada = getUnidadeLogadaCmd.get().getId();

		listaUnidades.stream().filter(unidade -> unidade.getIdUnidade() == idUnidadeLogada).forEach(u -> u.setUnidadeLogada(Boolean.TRUE));

	}

}
