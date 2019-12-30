package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramaTOBuilder;
import br.com.crux.dao.repository.ProgramaRepository;
import br.com.crux.entity.Programa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosProgramaRule;
import br.com.crux.to.ProgramaTO;

@Component
public class AlterarProgramaCmd {

	@Autowired private ProgramaRepository repository;
	@Autowired private CamposObrigatoriosProgramaRule camposObrigatoriosRule;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlterarProgramaUnidadesCmd alterarProgramaUnidadesCmd;
	@Autowired private AlterarListaColaboradoresProgramaCmd alterarListaColaboradoresProgramaCmd;
	@Autowired private AlterarParceriasProgramaCmd alterarParceriasProgramaCmd;
	@Autowired private AlterarListaComposicaoRhProgramaCmd alterarListaComposicaoRhProgramaCmd;

	public void alterar(ProgramaTO to) {
		Programa entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Programa informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = programaTOBuilder.build(to);

		Programa programa = repository.save(entity);

		alterarProgramaUnidadesCmd.alterarAll(to.getUnidades(), programa);

		alterarListaColaboradoresProgramaCmd.alterarAll(to.getColaboradoresPrograma(), programa);

		alterarParceriasProgramaCmd.alterarAll(to.getParceriasPrograma(), programa);
		
		alterarListaComposicaoRhProgramaCmd.alterarAll(to.getComposicaoRhPrograma(), programa);
		

	}
}
