package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OficinasTOBuilder;
import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.entity.Oficinas;
import br.com.crux.rule.CamposObrigatoriosAtividadeRule;
import br.com.crux.to.OficinasTO;
import br.com.crux.to.TurmasTO;

@Component
public class CadastrarOficinasCmd {

	@Autowired private OficinasRepository repository;
	@Autowired private OficinasTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosAtividadeRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarColaboradoresAtividadeCmd cadastrarColaboradoresAtividadeCmd;
	@Autowired private CadastrarMateriaisAtividadeCmd cadastrarMateriaisAtividadeCmd;
	
	public void cadastrar(OficinasTO oficinaTO) {
		camposObrigatoriosRule.verificar(oficinaTO);
		oficinaTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		Oficinas entity = toBuilder.build(oficinaTO);
		Oficinas oficina = repository.save(entity);
		
		cadastrarColaboradoresAtividadeCmd.cadastrarAll(oficinaTO.getColaboradoresAtividade(), oficina.getId());
		cadastrarMateriaisAtividadeCmd.cadastrarAll(oficinaTO.getMateriaisAtividade(), oficina.getId());
		
	}
	
	public void cadastrarAll(List<OficinasTO> oficinasTO, TurmasTO turmaTO) {
		
		oficinasTO.stream().forEach(oficinaTO -> {
			oficinaTO.setIdTurma(turmaTO.getId());
			cadastrar(oficinaTO);
		});
	
	}

}
