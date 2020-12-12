package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CadastroReservaAtividadeRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirCadastroReservaAtividadeCmd {

	@Autowired private CadastroReservaAtividadeRepository repository;

	public void excluir(Long id) {
		Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir o Cadastro Reserva Atividade. Parâmetro ausente."));
		repository.deleteById(id);
	}
}
