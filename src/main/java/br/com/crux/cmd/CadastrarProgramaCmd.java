package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramaTOBuilder;
import br.com.crux.dao.repository.ProgramaRepository;
import br.com.crux.entity.Programa;
import br.com.crux.rule.CamposObrigatoriosProgramaRule;
import br.com.crux.to.ProgramaTO;

@Component
public class CadastrarProgramaCmd {

	@Autowired private ProgramaRepository repository;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private CamposObrigatoriosProgramaRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarProgramaUnidadeCmd cadastrarProgramaUnidadeCmd;
	@Autowired private CadastrarListaColaboradoresProgramaCmd cadastrarListaColaboradoresProgramaCmd;
	@Autowired private CadastrarParceriaProgramaCmd cadastrarParceriaProgramaCmd;
	@Autowired private CadastrarComposicaoRhProgramaCmd cadastrarComposicaoRhProgramaCmd;

	public void cadastrar(ProgramaTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Programa entity = programaTOBuilder.build(to);

		Programa programa = repository.save(entity);

		cadastrarProgramaUnidadeCmd.cadastrarLista(programa, to.getUnidades());

		cadastrarComposicaoRhProgramaCmd.cadastrarLista(programa, to.getComposicaoRhPrograma());

		cadastrarListaColaboradoresProgramaCmd.cadastrarLista(programa, to.getColaboradoresPrograma());

		cadastrarParceriaProgramaCmd.cadastrarLista(programa, to.getParceriasPrograma());

	}
}
