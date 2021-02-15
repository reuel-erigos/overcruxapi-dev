package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncionarioTOBuilder;
import br.com.crux.dao.FuncionarioDao;
import br.com.crux.dao.dto.FuncionarioDTO;
import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.ComboFuncionarioTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class GetFuncionarioCmd {

	@Autowired private FuncionarioRepository repository;
	@Autowired private FuncionarioTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private FuncionarioDao funcionarioDao;

	
	public List<ComboFuncionarioTO> getAllByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<FuncionarioDTO> alunos = funcionarioDao.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllDTO(alunos);
	}
	
	public List<FuncionarioTO> getAllPorUnidadeLogada() {
		AcessoUnidadeTO acessoUnidadeTO = getUnidadeLogadaCmd.get();

		Optional<List<Funcionario>> funcionariosOptional = repository.findAllByIdUnidade(acessoUnidadeTO.getId());
		if (!funcionariosOptional.isPresent()) {
			return new ArrayList<FuncionarioTO>();
		}

		List<FuncionarioTO> lista = toBuilder.buildAll(funcionariosOptional.get());
		return lista;
	}

	public FuncionarioTO getTOById(Long id) {
		Funcionario entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public Funcionario getById(Long id) {
		Long idPresente = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Parâmetro ID ausente."));
		return repository.findById(idPresente).orElseGet(null);

	}

	public Funcionario getPorPessoa(Long idPessoa) {
		return repository.getPorPessoa(idPessoa).orElse(null);
	}

	public FuncionarioTO getPorPessoaFisica(Long idPessoa) {
		return toBuilder.buildTO(getPorPessoa(idPessoa));
	}

	public List<FuncionarioTO> getFuncionarioPorUnidades(List<Long> ids) {
		Optional<List<Funcionario>> funcionarios = repository.getPorUnidades(ids);

		if(funcionarios.isPresent()) {
			return toBuilder.buildAll(funcionarios.get());
		}

		return new ArrayList<FuncionarioTO>();
	}

	public List<FuncionarioTO> getFuncionarioPorInstituicao(Long id) {
		Optional<List<Funcionario>> funcionarios = repository.getPorInstituicao(id);
		if(funcionarios.isPresent()) {
			return toBuilder.buildAllTOCombo(funcionarios.get());
		}
		return new ArrayList<FuncionarioTO>();
	}

	public List<FuncionarioTO> getAllPorUnidadeLogadaCombo() {
		AcessoUnidadeTO acessoUnidadeTO = getUnidadeLogadaCmd.get();

		Optional<List<Funcionario>> funcionariosOptional = repository.findAllByIdUnidade(acessoUnidadeTO.getId());
		if (!funcionariosOptional.isPresent()) {
			return new ArrayList<FuncionarioTO>();
		}

		return toBuilder.buildAllTOCombo(funcionariosOptional.get());

	}

}
