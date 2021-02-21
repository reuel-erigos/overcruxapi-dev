package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoAcoesTOBuilder;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.rule.CamposObrigatoriosGrupoAcoesRule;
import br.com.crux.rule.ValidarMateriaisAcaoRule;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class CadastrarGrupoAcoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoAcoesRepository repository;
	@Autowired private GrupoAcoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosGrupoAcoesRule camposObrigatoriosRule;
	@Autowired private ValidarMateriaisAcaoRule validarMateriaisAcaoRule;
	@Autowired private CadastrarListaAcoesCmd cadastrarListaAcoesCmd;
	
	
	
	public GrupoAcoesTO cadastrar(GrupoAcoesTO to) {
		Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		camposObrigatoriosRule.verificar(to);
		
		if(Objects.nonNull(to.getAcoes())) {
			to.getAcoes().forEach(acaoTO -> {
				validarMateriaisAcaoRule.validar(acaoTO);
			});
		}
		
		to.setUsuarioAlteracao(idUsuarioLogado);
		GrupoAcoes entity = toBuilder.build(to);
		entity = repository.save(entity);
		
		cadastrarListaAcoesCmd.cadastrarAll(to.getAcoes(), entity.getId());
		
		//Retorna os dados atualizados
		return toBuilder.buildTO(repository.findById(entity.getId()).get());
		
	}
	
}
