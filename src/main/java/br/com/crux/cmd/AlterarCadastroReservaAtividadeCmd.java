package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CadastroReservaAtividadeTOBuilder;
import br.com.crux.dao.repository.CadastroReservaAtividadeRepository;
import br.com.crux.entity.CadastroReservaAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCadastroReservaAtividadeRule;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class AlterarCadastroReservaAtividadeCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastroReservaAtividadeRepository repository;
	@Autowired private CadastroReservaAtividadeTOBuilder cadastroReservaAtividadeTOBuilder;
	@Autowired private CamposObrigatoriosCadastroReservaAtividadeRule camposObrigatoriosRule;

	public void alterar(CadastroReservaAtividadeTO to) {

		camposObrigatoriosRule.verificar(to);

		CadastroReservaAtividade entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Cadastro reserva atividade informada não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = cadastroReservaAtividadeTOBuilder.build(to);

		repository.save(entity);

	}
}
