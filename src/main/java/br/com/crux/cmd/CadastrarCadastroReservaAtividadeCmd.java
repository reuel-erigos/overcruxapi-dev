package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CadastroReservaAtividadeTOBuilder;
import br.com.crux.dao.repository.CadastroReservaAtividadeRepository;
import br.com.crux.entity.CadastroReservaAtividade;
import br.com.crux.rule.CamposObrigatoriosCadastroReservaAtividadeRule;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class CadastrarCadastroReservaAtividadeCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastroReservaAtividadeRepository repository;
	@Autowired private CadastroReservaAtividadeTOBuilder cadastroReservaAtividadeTOBuilder;
	@Autowired private CamposObrigatoriosCadastroReservaAtividadeRule camposObrigatoriosRule;

	public void cadastrar(CadastroReservaAtividadeTO to) {
		
		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		CadastroReservaAtividade entity = cadastroReservaAtividadeTOBuilder.build(to);

		repository.save(entity);

	}
}
