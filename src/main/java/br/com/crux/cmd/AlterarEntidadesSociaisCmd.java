package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.EmpresaRepository;
import br.com.crux.dao.repository.EntidadesSociaisRepository;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.EntidadesSociais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosEntidadesSociaisRule;
import br.com.crux.to.EntidadesSociaisTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarEntidadesSociaisCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private EntidadesSociaisRepository repository;
	@Autowired private CamposObrigatoriosEntidadesSociaisRule camposObrigatoriosRule;
	@Autowired private EmpresaRepository empresaRepository;
	@Autowired private AlterarEmpresaCmd alterarEmpresaCmd;
	
	public void alterar(EntidadesSociaisTO to) {
		EntidadesSociais entidadeSocial = repository.findById(to.getId()).orElseThrow( () -> new NotFoundException("Entidade Social informada não existe.") );
		
		camposObrigatoriosRule.verificar(to);
		
		Optional<Empresa> empresaOptional = empresaRepository.findById(to.getEmpresa().getId());
		if(!empresaOptional.isPresent()) {
			throw new NotFoundException("Empresa informado não existe.");
		}
		
		EntidadesSociais entity = entidadeSocial;

		entity.setDataFim(to.getDataFim());
		entity.setDataVinculo(to.getDataVinculo());
		entity.setEmpresa(alterarEmpresaCmd.alterar(to.getEmpresa()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
