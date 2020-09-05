package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EmpresaTOBuilder;
import br.com.crux.dao.repository.EmpresaRepository;
import br.com.crux.entity.Empresa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EmpresaTO;

@Component
public class GetEmpresaCmd {

	@Autowired private EmpresaRepository repository;
	@Autowired private EmpresaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<EmpresaTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Empresa>> lista = repository.getAllByInstituicao(idInstituicao);
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

}
