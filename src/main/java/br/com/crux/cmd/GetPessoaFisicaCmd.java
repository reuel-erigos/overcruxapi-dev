package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.FornecedorColaboradorDao;
import br.com.crux.dao.PessoaFisicaDao;
import br.com.crux.dao.dto.FornecedorColaboradorDTO;
import br.com.crux.dao.dto.PessoaFisicaDTO;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ComboPessoaFisicaTO;
import br.com.crux.to.FornecedorColaboradorTO;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class GetPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private PessoaFisicaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private FornecedorColaboradorDao fornecedorColaboradorDao;
	@Autowired private PessoaFisicaDao pessoaFisicaDao;
	
	public List<ComboPessoaFisicaTO> getAllByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<PessoaFisicaDTO> pessoas = pessoaFisicaDao.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllComboDTO(pessoas);
	}

	public List<FornecedorColaboradorTO> getAllFornecedoresColaboradores() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		List<FornecedorColaboradorDTO> entitys = fornecedorColaboradorDao.getAll(idInstituicao);
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<FornecedorColaboradorTO>();
		}
		return toBuilder.buildAllDTO(entitys);
	}
	
	public List<PessoaFisicaTO> getAll() {
		List<PessoaFisica> entitys = repository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<PessoaFisicaTO>();
		}
		return toBuilder.buildAll(entitys);
	}

	public PessoaFisicaTO getTOById(Long id) {
		PessoaFisica entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa física não encontrado."));
		return toBuilder.buildTO(entity);
	}
	
	public PessoaFisicaTO getTOByCpf(String cpf) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		cpf = cpf.replace(".", "").replace("-", "");
		PessoaFisica entity = repository.findByCpfAndInstituicao(cpf, idInstituicao).orElseGet(null);
		if(entity != null) {
			return toBuilder.buildTO(entity);
		} 
		return null;
	}

	public PessoaFisica getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
