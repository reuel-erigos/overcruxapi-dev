package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.FuncionarioRepository;
import br.com.crux.entity.Funcionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirFuncionarioCmd {

	@Autowired private FuncionarioRepository repository;
	@Autowired private ExcluirPessoaFisicaCmd excluirPessoaFisicaCmd;

	public void excluir(Long idFuncionario) {

		try {
			if (Objects.isNull(idFuncionario)) {
				throw new ParametroNaoInformadoException("Erro ao excluir.");
			}
			
			Funcionario funcionario = repository.findById(idFuncionario).orElseThrow(() -> new NotFoundException("Funcionario informada não existe."));
			
			repository.delete(funcionario);
			
			excluirPessoaFisicaCmd.excluir(funcionario.getPessoasFisica());
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este funcionário.");
		}	

	}
}
