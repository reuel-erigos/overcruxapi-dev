package br.com.crux.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Programa;
import br.com.crux.entity.ProgramasUnidade;
import br.com.crux.entity.Unidade;

@Component
public class ProgramasUnidadeTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public ProgramasUnidade build(Programa programa, Unidade unidade) {
		ProgramasUnidade programaUnidade = new ProgramasUnidade();
		programaUnidade.setPrograma(programa);
		programaUnidade.setUnidade(unidade);
		programaUnidade.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return programaUnidade;
	}

}
