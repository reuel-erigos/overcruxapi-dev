package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PrestacoesContaTOBuilder;
import br.com.crux.dao.repository.PrestacoesContaRepository;
import br.com.crux.entity.PrestacoesConta;
import br.com.crux.entity.Programa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PrestacoesContaTO;

@Component
public class GetPrestacoesContaCmd {

	@Autowired private PrestacoesContaRepository repository;
	@Autowired private PrestacoesContaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<PrestacoesContaTO> getAll() {
//
//		Long idUnidade = getUnidadeLogadaCmd.get().getId();
//		Optional<List<PrestacoesConta>> entitys = repository.findByPrograma(new Programa());
//		if (entitys.isPresent()) {
//			return toBuilder.buildAll(entitys.get());
//		}
		return new ArrayList<PrestacoesContaTO>();

	}

	public PrestacoesContaTO getTOById(Long idPrestacoesConta) {
		PrestacoesConta prestacoesConta = repository.findById(idPrestacoesConta).orElseThrow(() -> new NotFoundException("Prestação de Conta não encontrado"));
		return toBuilder.buildTO(prestacoesConta);
	}

	public PrestacoesConta getById(Long idPrestacoesConta) {
		return repository.findById(idPrestacoesConta).orElseGet(null);
	}

}
