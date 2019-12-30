package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaltasFuncionarioTOBuilder;
import br.com.crux.dao.repository.FaltasFuncionarioRepository;
import br.com.crux.entity.FaltasFuncionario;
import br.com.crux.rule.CamposObrigatoriosFaltasFuncionarioRule;
import br.com.crux.to.FaltasFuncionarioTO;

@Component
public class CadastrarFaltasFuncionarioCmd {

	@Autowired private FaltasFuncionarioRepository repository;

	@Autowired private FaltasFuncionarioTOBuilder faltasFuncionarioTOBuilder;
	@Autowired private CamposObrigatoriosFaltasFuncionarioRule camposObrigatoriosCargosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(FaltasFuncionarioTO to) {

		camposObrigatoriosCargosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		FaltasFuncionario faltasFuncionario = faltasFuncionarioTOBuilder.build(to);

		repository.save(faltasFuncionario);

	}
}
