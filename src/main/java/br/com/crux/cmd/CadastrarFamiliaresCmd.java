package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FamiliaresTOBuilder;
import br.com.crux.dao.repository.FamiliaresRepository;
import br.com.crux.entity.Familiares;
import br.com.crux.rule.CamposObrigatoriosFamiliaresRule;
import br.com.crux.to.FamiliaresTO;

@Component
public class CadastrarFamiliaresCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private FamiliaresRepository repository;
	@Autowired private CamposObrigatoriosFamiliaresRule camposObrigatoriosRule;
	@Autowired private FamiliaresTOBuilder familiaresTOBuilder;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	@Autowired private AlterarPessoaFisicaCmd alterarResponsaveisAlunoCmd;
	
	@Autowired private CadastrarResponsaveisAlunoCmd cadastrarResponsaveisAlunoCmd;
	@Autowired private CadastrarVulnerabilidadesFamiliarCmd cadastrarVulnerabilidadesFamiliarCmd;
	
	public FamiliaresTO cadastrar(FamiliaresTO to) {
		camposObrigatoriosRule.verificar(to);
		
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Familiares entity = familiaresTOBuilder.build(to);
		
		if(to.getPessoasFisica().getId() == null) {
			entity.setPessoasFisica(cadastrarPessoaFisicaCmd.cadastrar(to.getPessoasFisica()));
		} else {
			entity.setPessoasFisica(alterarResponsaveisAlunoCmd.alterar(to.getPessoasFisica()));
		}
		FamiliaresTO familiarTOSalvo = familiaresTOBuilder.buildTO(repository.save(entity));
		
		if(!Objects.isNull(to.getResponsaveis())) {
			cadastrarResponsaveisAlunoCmd.cadastrar(to.getResponsaveis(), familiarTOSalvo);
		}
		if(!Objects.isNull(to.getVulnerabilidades())) {
			cadastrarVulnerabilidadesFamiliarCmd.cadastrar(to.getVulnerabilidades(), familiarTOSalvo);
		}
		
		return familiarTOSalvo;
	}
}
