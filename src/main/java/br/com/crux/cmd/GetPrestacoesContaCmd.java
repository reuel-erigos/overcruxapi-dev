package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PrestacoesContaTOBuilder;
import br.com.crux.dao.repository.PrestacoesContaRepository;
import br.com.crux.entity.PrestacoesConta;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PrestacoesContaTO;

@Component
public class GetPrestacoesContaCmd {

	@Autowired private PrestacoesContaRepository repository;
	@Autowired private PrestacoesContaTOBuilder toBuilder;


	public PrestacoesContaTO getTOById(Long idPrestacoesConta) {
		PrestacoesConta prestacoesConta = repository.findById(idPrestacoesConta).orElseThrow(() -> new NotFoundException("Prestação de Conta não encontrado"));
		return toBuilder.buildTO(prestacoesConta);
	}

	public PrestacoesConta getById(Long idPrestacoesConta) {
		return repository.findById(idPrestacoesConta).orElseGet(null);
	}

}
