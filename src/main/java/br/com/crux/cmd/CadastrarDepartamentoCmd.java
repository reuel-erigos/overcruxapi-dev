package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DepartamentoTOBuilder;
import br.com.crux.dao.repository.DepartamentoRepository;
import br.com.crux.entity.Departamentos;
import br.com.crux.rule.CamposObrigatoriosDepartamentoRule;
import br.com.crux.to.DepartamentoTO;

@Component
public class CadastrarDepartamentoCmd {

	@Autowired private DepartamentoRepository departamentoRepository;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private CamposObrigatoriosDepartamentoRule camposObrigatoriosDepartamentoRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(DepartamentoTO to) {

		camposObrigatoriosDepartamentoRule.verificar(to);
		
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Departamentos entity = departamentoTOBuilder.build(to);

		departamentoRepository.save(entity);

	}
}
