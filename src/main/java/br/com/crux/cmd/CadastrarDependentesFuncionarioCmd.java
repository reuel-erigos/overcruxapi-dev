package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DependentesTOBuilder;
import br.com.crux.dao.repository.DependentesRepository;
import br.com.crux.entity.Dependentes;
import br.com.crux.rule.CamposObrigatoriosDependentesFuncionarioRule;
import br.com.crux.to.DependentesTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class CadastrarDependentesFuncionarioCmd {

	@Autowired private DependentesTOBuilder toBuilder;
	@Autowired private DependentesRepository repository;
	@Autowired private CamposObrigatoriosDependentesFuncionarioRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	

	public void cadastrar(List<DependentesTO> dependentesTO, FuncionarioTO funcionarioTO) {
		Optional.ofNullable(dependentesTO).ifPresent(listaTO -> {
			if(!listaTO.isEmpty()) {
				listaTO.stream().forEach(dependenteTO -> {
					camposObrigatoriosRule.verificar(dependenteTO);
					
					dependenteTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
					
					dependenteTO.setIdFuncionario(funcionarioTO.getId());
					Dependentes entity = toBuilder.build(dependenteTO);
					entity.setPessoaFisica(cadastrarPessoaFisicaCmd.cadastrar(dependenteTO.getPessoaFisica()));
					
					repository.save(entity);
				});
			}
		});
	
	}
}
