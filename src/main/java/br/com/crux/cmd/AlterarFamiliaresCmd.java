package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FamiliaresTOBuilder;
import br.com.crux.dao.repository.FamiliaresRepository;
import br.com.crux.entity.Familiares;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFamiliaresRule;
import br.com.crux.to.FamiliaresTO;

@Component
public class AlterarFamiliaresCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private FamiliaresRepository repository;
	@Autowired private CamposObrigatoriosFamiliaresRule camposObrigatoriosRule;
	@Autowired private FamiliaresTOBuilder familiaresTOBuilder;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private AlterarResponsaveisAlunoCmd alterarResponsaveisAlunoCmd;
	@Autowired private AlterarVulnerabilidadesFamiliarCmd alterarVulnerabilidadesFamiliarCmd;
	
	public FamiliaresTO alterar(FamiliaresTO familiarTO) {
		camposObrigatoriosRule.verificar(familiarTO);
		
		Familiares familiar = repository.findById(familiarTO.getId()).orElseThrow(() -> new NotFoundException("Familiar informado não existe."));
		
		familiarTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		familiar = familiaresTOBuilder.build(familiarTO);
		
		familiar.setPessoasFisica(alterarPessoaFisicaCmd.alterar(familiarTO.getPessoasFisica()));
		
		alterarResponsaveisAlunoCmd.alterarAll(familiarTO.getResponsaveis(), familiarTO);
		
		alterarVulnerabilidadesFamiliarCmd.alterarAll(familiarTO.getVulnerabilidades(), familiarTO);
		
		Familiares familiarSalvo = repository.save(familiar);
		return familiaresTOBuilder.buildTO(familiarSalvo);
	}
	
}
