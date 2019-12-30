package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaltasFuncionarioTOBuilder;
import br.com.crux.dao.repository.FaltasFuncionarioRepository;
import br.com.crux.entity.FaltasFuncionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFaltasFuncionarioRule;
import br.com.crux.to.FaltasFuncionarioTO;

@Component
public class AlterarFaltasFuncionarioCmd {

	@Autowired private FaltasFuncionarioRepository repository;
	@Autowired private FaltasFuncionarioTOBuilder faltasFuncionarioTOBuilder;
	@Autowired private CamposObrigatoriosFaltasFuncionarioRule camposObrigatoriosCargosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetFaltasFuncionarioCmd getFaltasFuncionarioCmd;

	public void alterar(FaltasFuncionarioTO to) {

		camposObrigatoriosCargosRule.verificar(to);

		FaltasFuncionario entity = Optional.ofNullable(getFaltasFuncionarioCmd.getById(to.getId())).orElseThrow(() -> new NotFoundException("Faltas do Funcionario não encontrada."));

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = faltasFuncionarioTOBuilder.build(to);

		repository.save(entity);

	}
}
