package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CotacoesMaterialTOBuilder;
import br.com.crux.dao.repository.CotacoesMaterialRepository;
import br.com.crux.entity.CotacoesMaterial;
import br.com.crux.rule.CamposObrigatoriosCotacoesMaterialRule;
import br.com.crux.to.CotacoesMaterialTO;

@Component
public class CadastrarCotacoesMaterialCmd {

	@Autowired private CotacoesMaterialRepository repository;
	@Autowired private CotacoesMaterialTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosCotacoesMaterialRule rule;

	public void cadastrar(CotacoesMaterialTO to) {

		rule.verificar(to);

		CotacoesMaterial entity = toBuilder.build(to);

		repository.save(entity);

	}
}
