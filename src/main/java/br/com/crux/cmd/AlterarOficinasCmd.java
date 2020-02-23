package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OficinasTOBuilder;
import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.entity.Oficinas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAtividadeRule;
import br.com.crux.to.OficinasTO;
import br.com.crux.to.TurmasTO;

@Component
public class AlterarOficinasCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private OficinasRepository repository;
	@Autowired private OficinasTOBuilder atividadesTOBuilder;
	@Autowired private AlterarColaboradesAtividadeCmd alterarColaboradesAtividadeCmd;
	@Autowired private AlterarMateriaisAtividadeCmd alterarMateriaisAtividadeCmd;

	@Autowired private CamposObrigatoriosAtividadeRule camposObrigatoriosRule;
	@Autowired private CadastrarOficinasCmd cadastrarAtividadesCmd;
	@Autowired private AlterarAcaoCmd alterarAcaoCmd;
	
	
	public void alterar(OficinasTO to) {
		camposObrigatoriosRule.verificar(to);

		if(Objects.isNull(to.getId())) {
			cadastrarAtividadesCmd.cadastrar(to);
		} else {
			Oficinas entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Atividade informado não existe."));
			to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			
			entity = atividadesTOBuilder.build(to);
			repository.save(entity);
			
			alterarColaboradesAtividadeCmd.alterarAll(to.getColaboradoresAtividade(),entity.getId());
			alterarMateriaisAtividadeCmd.alterarAll(to.getMateriaisAtividade(), entity.getId());
			alterarAcaoCmd.alterarAll(to.getAcoes(), entity.getId());
		}

	}
	
	public void alterarAll(List<OficinasTO> lista, TurmasTO turmaTO) {
		lista.forEach(atividade -> {
			atividade.setIdTurma(turmaTO.getId());
			alterar(atividade);
		});
	}
	
	
	
}
