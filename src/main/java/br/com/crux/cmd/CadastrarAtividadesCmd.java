package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesTOBuilder;
import br.com.crux.dao.repository.AtividadeRepository;
import br.com.crux.entity.Atividades;
import br.com.crux.rule.CamposObrigatoriosAtividadeRule;
import br.com.crux.to.AtividadesTO;
import br.com.crux.to.TurmasTO;

@Component
public class CadastrarAtividadesCmd {

	@Autowired private AtividadeRepository repository;
	@Autowired private AtividadesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosAtividadeRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarColaboradoresAtividadeCmd cadastrarColaboradoresAtividadeCmd;
	@Autowired private CadastrarMateriaisAtividadeCmd cadastrarMateriaisAtividadeCmd;

	public void cadastrar(AtividadesTO atividadeTO) {
		camposObrigatoriosRule.verificar(atividadeTO);
		atividadeTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		Atividades entity = toBuilder.build(atividadeTO);
		Atividades atividade = repository.save(entity);
		
		cadastrarColaboradoresAtividadeCmd.cadastrarAll(atividadeTO.getColaboradoresAtividade(), atividade.getId());
		cadastrarMateriaisAtividadeCmd.cadastrarAll(atividadeTO.getMateriaisAtividade(), atividade.getId());
		
	}
	
	public void cadastrarAll(List<AtividadesTO> atividadesTO, TurmasTO turmaTO) {
		
		atividadesTO.stream().forEach(atividadeTO -> {
			atividadeTO.setIdTurma(turmaTO.getId());
			cadastrar(atividadeTO);
		});
	
	}

}
