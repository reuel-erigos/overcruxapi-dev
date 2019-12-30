package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PerspectivaTOBuilder;
import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.entity.Perspectiva;
import br.com.crux.rule.CamposObrigatoriosPerspectivaRule;
import br.com.crux.to.PerspectivaTO;

@Component
public class CadastrarPerspectivaCmd {

	@Autowired private PerspectivaRepository perspectivaRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosPerspectivaRule camposObrigatoriosPerspectivaRule;
	@Autowired private PerspectivaTOBuilder perspectivaTOBuilder;

	public void cadastrar(PerspectivaTO to) {

		camposObrigatoriosPerspectivaRule.verificar(to);

		to.setUsuariosSistema(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Perspectiva entity = perspectivaTOBuilder.build(to);

		perspectivaRepository.save(entity);

	}
}
