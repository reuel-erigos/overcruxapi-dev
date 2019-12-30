package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IniciativaTOBuilder;
import br.com.crux.dao.repository.IniciativaRepository;
import br.com.crux.entity.Iniciativa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosIniciativaRule;
import br.com.crux.to.IniciativaTO;

@Component
public class AlterarIniciativaCmd {

	@Autowired private IniciativaRepository iniciativaRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosIniciativaRule camposObrigatoriosRule;
	@Autowired private IniciativaTOBuilder iniciativaTOBuilder;

	public void alterar(IniciativaTO to) {
		camposObrigatoriosRule.verificar(to);

		Iniciativa entity = iniciativaRepository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Iniciativa informada não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = iniciativaTOBuilder.build(to);

		iniciativaRepository.save(entity);

	}
}
