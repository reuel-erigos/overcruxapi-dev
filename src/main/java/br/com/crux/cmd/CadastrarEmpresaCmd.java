package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EmpresaTOBuilder;
import br.com.crux.dao.repository.EmpresaRepository;
import br.com.crux.entity.Empresa;
import br.com.crux.rule.CamposObrigatoriosEmpresaRule;
import br.com.crux.to.EmpresaTO;

@Component
public class CadastrarEmpresaCmd {

	@Autowired private EmpresaRepository repository;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosEmpresaRule camposObrigatoriosRule;
	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	
	public Empresa cadastrar(EmpresaTO to) {
		camposObrigatoriosRule.verificar(to.getCodigo(), to.getNomeRazaoSocial(), to.getTipoEmpresa(), to.getTelefone(), to.getEndereco());
		
		Empresa entity = empresaTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		Empresa empresaSalva = repository.save(entity);
		return empresaSalva;
		
	}
}
