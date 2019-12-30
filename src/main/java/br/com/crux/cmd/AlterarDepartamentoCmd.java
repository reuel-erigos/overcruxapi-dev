package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DepartamentoTOBuilder;
import br.com.crux.dao.repository.DepartamentoRepository;
import br.com.crux.entity.Departamentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosDepartamentoRule;
import br.com.crux.to.DepartamentoTO;

@Component
public class AlterarDepartamentoCmd {

	@Autowired private DepartamentoRepository departamentoRepository;
	@Autowired private CamposObrigatoriosDepartamentoRule camposObrigatoriosDepartamentoRule;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd ;

	public void alterar(DepartamentoTO to) {
		Departamentos entity = departamentoRepository.findById(to.getIdDepartamento()).orElseThrow(() -> new NotFoundException("Departamento informado não existe."));

		camposObrigatoriosDepartamentoRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity = departamentoTOBuilder.build(to);

		departamentoRepository.save(entity);

	}

}
