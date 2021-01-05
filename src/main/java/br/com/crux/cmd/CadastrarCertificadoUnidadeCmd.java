package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CertificadoUnidadeTOBuilder;
import br.com.crux.dao.repository.CertificadoUnidadeRepository;
import br.com.crux.entity.CertificadoUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.rule.CamposObrigatoriosCertificadoUnidadeRule;
import br.com.crux.to.CertificadoUnidadeTO;

@Component
public class CadastrarCertificadoUnidadeCmd {
	
	@Autowired private CertificadoUnidadeRepository repository;
	@Autowired private CertificadoUnidadeTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosCertificadoUnidadeRule camposObrigatoriosRule;
	
	
	public void cadastrar(Unidade unidade, CertificadoUnidadeTO to) {
		
		camposObrigatoriosRule.verificar(to,unidade);
		
		CertificadoUnidade entity = toBuilder.build(to,unidade);
		
		repository.save(entity);
		
	}
}
