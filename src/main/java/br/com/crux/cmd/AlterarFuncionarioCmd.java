package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncionarioTOBuilder;
import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFuncionarioRule;
import br.com.crux.to.FuncionarioTO;

@Component
public class AlterarFuncionarioCmd {

	@Autowired private FuncionarioRepository repository;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private CamposObrigatoriosFuncionarioRule camposObrigatoriosFuncionarioRule;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlterarDependentesCmd alterarDependentesCmd;
	@Autowired private AlterarAlocacaoFuncionarioCmd alterarAlocacaoFuncionarioCmd;

	public FuncionarioTO alterar(FuncionarioTO to) {
		
		camposObrigatoriosFuncionarioRule.verificar(to);

		Funcionario funcionario = repository.findById(to.getId()).orElseThrow((() -> new NotFoundException("Funcionario informado não existe.")));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		funcionario = funcionarioTOBuilder.build(to);
		
		funcionario.setPessoasFisica(alterarPessoaFisicaCmd.alterar(to.getPessoasFisica()));

		alterarDependentesCmd.alterarAll(to.getDependentes(), to);
		alterarAlocacaoFuncionarioCmd.alterarAll(to.getAlocacoesFuncionario(), to);
		
		Funcionario funcionarioSalvo = repository.save(funcionario);
		return funcionarioTOBuilder.buildTO(funcionarioSalvo);

	}
}
