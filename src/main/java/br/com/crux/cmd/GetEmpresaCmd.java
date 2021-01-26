package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EmpresaTOBuilder;
import br.com.crux.dao.EmpresaDAO;
import br.com.crux.dao.dto.EmpresaDTO;
import br.com.crux.dao.repository.EmpresaRepository;
import br.com.crux.entity.Empresa;
import br.com.crux.enums.TipoEmpresa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ComboEmpresaTO;
import br.com.crux.to.EmpresaTO;

@Component
public class GetEmpresaCmd {

	@Autowired private EmpresaRepository repository;
	@Autowired private EmpresaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private EmpresaDAO empresaDAO;

	public List<EmpresaTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Empresa>> lista = repository.getAllByInstituicao(idInstituicao);
		if(lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		return new ArrayList<EmpresaTO>();
	}

	public List<EmpresaTO> getAllPorTipo(String tipo) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		List<TipoEmpresa> tipos = getTipos(tipo);
		
		Optional<List<Empresa>> lista = repository.getAllPorTipoByInstituicao(idInstituicao,tipos);
		if(lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		return new ArrayList<EmpresaTO>();
	}

	public EmpresaTO getTOById(Long id) {
		Optional<Empresa> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Empresa não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public Empresa getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<EmpresaTO> getAllCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Empresa>> entitys = repository.getAllByInstituicao(idInstituicao);
		if(entitys.isPresent()) {
			return toBuilder.buildAllCombo(entitys.get());
		}
		return new ArrayList<EmpresaTO>();
	}
	
	public List<ComboEmpresaTO> getAllByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<EmpresaDTO> empresas = empresaDAO.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllComboDTO(empresas);
	}

	
	private List<TipoEmpresa> getTipos(String tipo){
		
		if(tipo.equalsIgnoreCase("P")) {
			return Arrays.asList(TipoEmpresa.PARCEIRA, TipoEmpresa.PARCEIRAFORNECEDOR,TipoEmpresa.PARCEIRACLIENTEFORNECEDOR,TipoEmpresa.PARCEIRACLIENTE);
		}
		
		if(tipo.equalsIgnoreCase("F")) {
			return Arrays.asList(TipoEmpresa.FORNECEDOR, TipoEmpresa.PARCEIRAFORNECEDOR,TipoEmpresa.PARCEIRACLIENTEFORNECEDOR,TipoEmpresa.FORNECEDORCLIENTE);
		}

		if(tipo.equalsIgnoreCase("C")) {
			return Arrays.asList(TipoEmpresa.CLIENTE, TipoEmpresa.PARCEIRACLIENTEFORNECEDOR,TipoEmpresa.PARCEIRACLIENTE,TipoEmpresa.FORNECEDORCLIENTE);
		}
		
		
		
		return Collections.emptyList();
	}
}
