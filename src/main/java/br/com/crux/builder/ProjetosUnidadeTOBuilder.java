package br.com.crux.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.ProjetosUnidade;
import br.com.crux.entity.Unidade;

@Component
public class ProjetosUnidadeTOBuilder {

	@Autowired
	GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public ProjetosUnidade build(Projeto projeto, Unidade unidade) {
		ProjetosUnidade projetosUnidade = new ProjetosUnidade();
		projetosUnidade.setProjeto(projeto);
		projetosUnidade.setUnidade(unidade);
		projetosUnidade.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return projetosUnidade;
	}


}
