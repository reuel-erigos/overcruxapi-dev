package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProjetoTOBuilder;
import br.com.crux.dao.repository.ProjetoRepository;
import br.com.crux.entity.Projeto;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosProjetoRule;
import br.com.crux.to.ProjetoTO;

@Component
public class AlterarProjetoCmd {

	@Autowired private ProjetoRepository repository;
	@Autowired private CamposObrigatoriosProjetoRule camposObrigatoriosRule;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private AlterarProjetoUnidadesCmd alterarProjetoUnidadesCmd;
	@Autowired private AlterarParceriasProjetoCmd alterarParceriasProjetoCmd;
	@Autowired private AlterarListaColaboradoresProjetoCmd alterarListaColaboradoresProjetoCmd;
	@Autowired private AlterarListaComposicaoRhProjetoCmd alterarListaComposicaoRhProjetoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void alterar(ProjetoTO to) {

		camposObrigatoriosRule.verificar(to);

		Projeto entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Projeto informado não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = projetoTOBuilder.build(to);

		Projeto projeto = repository.save(entity);

		alterarProjetoUnidadesCmd.alterarAll(to.getUnidades(), projeto);

		alterarListaColaboradoresProjetoCmd.alterarAll(to.getColaboradoresProjeto(), projeto);

		alterarParceriasProjetoCmd.alterarAll(to.getParceriasProjeto(), projeto);

		alterarListaComposicaoRhProjetoCmd.alterarAll(to.getComposicaoRhProjeto(), projeto);

		
		

	}
}
