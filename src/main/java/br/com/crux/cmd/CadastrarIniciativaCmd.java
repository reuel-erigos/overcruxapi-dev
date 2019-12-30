package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IniciativaTOBuilder;
import br.com.crux.dao.repository.IniciativaRepository;
import br.com.crux.entity.Iniciativa;
import br.com.crux.rule.CamposObrigatoriosIniciativaRule;
import br.com.crux.to.IniciativaTO;

@Component
public class CadastrarIniciativaCmd {

	@Autowired private IniciativaRepository iniciativaRepository;
	@Autowired private CamposObrigatoriosIniciativaRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private IniciativaTOBuilder iniciativaTOBuilder;

	public void cadastrar(IniciativaTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Iniciativa entity = iniciativaTOBuilder.build(to);

		iniciativaRepository.save(entity);

	}
}
