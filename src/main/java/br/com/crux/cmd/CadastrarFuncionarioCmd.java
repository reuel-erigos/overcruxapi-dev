package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncionarioTOBuilder;
import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.rule.CamposObrigatoriosFuncionarioRule;
import br.com.crux.to.FuncionarioTO;

@Component
public class CadastrarFuncionarioCmd {

	@Autowired private FuncionarioRepository repository;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private CamposObrigatoriosFuncionarioRule camposObrigatoriosFuncionarioRule;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarDependentesFuncionarioCmd cadastrarDependentesFuncionarioCmd;
	@Autowired private CadastrarAlocacoesFuncionarioCmd cadastrarAlocacoesFuncionarioCmd;

	public FuncionarioTO cadastrar(FuncionarioTO to) {
		camposObrigatoriosFuncionarioRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Funcionario entity = funcionarioTOBuilder.build(to);

		entity.setPessoasFisica(cadastrarPessoaFisicaCmd.cadastrar(to.getPessoasFisica()));
		FuncionarioTO funcionarioTOSalvo = funcionarioTOBuilder.buildTO(repository.save(entity));
		
		cadastrarDependentesFuncionarioCmd.cadastrar(to.getDependentes(), funcionarioTOSalvo);
		cadastrarAlocacoesFuncionarioCmd.cadastrar(to.getAlocacoesFuncionario(), funcionarioTOSalvo);

		return funcionarioTOSalvo;
	}
	
	
}
