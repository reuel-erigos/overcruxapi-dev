package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisAcoesTOBuilder;
import br.com.crux.dao.repository.MateriaisAcoesRepository;
import br.com.crux.entity.MateriaisAcoes;
import br.com.crux.rule.CamposObrigatoriosMateriaisAcoesRule;
import br.com.crux.to.MateriaisAcoesTO;

@Component
public class CadastrarMateriaisAcoesCmd {

	@Autowired private MateriaisAcoesRepository repository;
	@Autowired private MateriaisAcoesTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosMateriaisAcoesRule camposObrigatoriosRule;

	private void cadastrar(MateriaisAcoesTO to) {
		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		MateriaisAcoes entity = toBuilder.build(to);
		repository.save(entity);

	}
	

	public void cadastrarAll(List<MateriaisAcoesTO> materiaisAcoes, Long idAcao) {
		materiaisAcoes.forEach(materialAcao -> {
			materialAcao.setIdAcao(idAcao);
			cadastrar(materialAcao); 
		});
	}
}
