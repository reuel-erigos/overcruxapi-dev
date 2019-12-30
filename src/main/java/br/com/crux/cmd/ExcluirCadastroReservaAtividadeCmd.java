package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CadastroReservaAtividadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirCadastroReservaAtividadeCmd {

	@Autowired private CadastroReservaAtividadeRepository repository;

	public void excluir(Long id) {
		
		
		try {
			Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir o Cadastro Reserva Atividade. Parâmetro ausente."));
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta reserva de atividade.");
		}	
		
	}
}
