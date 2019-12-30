package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.EntidadesSociaisRepository;
import br.com.crux.entity.EntidadesSociais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosEntidadesSociaisRule;
import br.com.crux.to.EntidadesSociaisTO;

@Component
public class CadastrarEntidadesSociaisCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private EntidadesSociaisRepository repository;
	@Autowired private CamposObrigatoriosEntidadesSociaisRule camposObrigatoriosRule;
	@Autowired private CadastrarEmpresaCmd cadastrarEmpresaCmd;
	
	public void cadastrar(EntidadesSociaisTO to) {
		
		if(Objects.isNull(to.getEmpresa())) {
			throw new NotFoundException("Empresa não informada.");
		}
		
		camposObrigatoriosRule.verificar(to);
		
		EntidadesSociais entity = new EntidadesSociais();

		entity.setDataFim(to.getDataFim());
		entity.setDataVinculo(to.getDataVinculo());
		entity.setEmpresa(cadastrarEmpresaCmd.cadastrar(to.getEmpresa()));
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		repository.save(entity);
		
	}
}
