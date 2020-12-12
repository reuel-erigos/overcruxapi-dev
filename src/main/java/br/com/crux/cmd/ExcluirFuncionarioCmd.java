package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirFuncionarioCmd {

	@Autowired private FuncionarioRepository repository;
	@Autowired private ExcluirPessoaFisicaCmd excluirPessoaFisicaCmd;

	public void excluir(Long idFuncionario) {
		if (Objects.isNull(idFuncionario)) {
			throw new ParametroNaoInformadoException("Erro ao excluir.");
		}
		
		Funcionario funcionario = repository.findById(idFuncionario).orElseThrow(() -> new NotFoundException("Funcionario informada não existe."));
		
		repository.delete(funcionario);
		
		excluirPessoaFisicaCmd.excluir(funcionario.getPessoasFisica());
	}
}
