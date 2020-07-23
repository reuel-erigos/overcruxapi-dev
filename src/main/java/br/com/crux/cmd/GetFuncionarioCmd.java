package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncionarioTOBuilder;
import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class GetFuncionarioCmd {

	@Autowired
	private FuncionarioRepository repository;
	@Autowired
	private FuncionarioTOBuilder toBuilder;
	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

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
		return repository.getPorPessoa(idPessoa).orElseThrow(() -> new NotFoundException("Funcionário não encontrado."));
	}

	public FuncionarioTO getPorPessoaFisica(Long idPessoa) {
		return toBuilder.buildTO(getPorPessoa(idPessoa));
	}

	public List<FuncionarioTO> getFuncionarioPorUnidades(List<Long> ids) {
		List<Funcionario> lista = repository.getPorUnidades(ids).orElseThrow(() -> new NotFoundException("Funcionários não encontrado."));

		return toBuilder.buildAll(lista);

	}

	public List<FuncionarioTO> getFuncionarioPorInstituicao(Long id) {
		List<Funcionario> lista = repository.getPorInstituicao(id).orElseThrow(() -> new NotFoundException("Funcionários da Instituição não encontrado."));
		return toBuilder.buildAllTOCombo(lista);

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
