
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CertificadoUnidadeTOBuilder;
import br.com.crux.dao.repository.CertificadoUnidadeRepository;
import br.com.crux.entity.CertificadoUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CertificadoUnidadeTO;

@Component
public class GetCertificadoUnidadeCmd {

	@Autowired
	private CertificadoUnidadeRepository repository;
	@Autowired
	private CertificadoUnidadeTOBuilder toBuilder;

	public List<CertificadoUnidadeTO> getAll() {
		List<CertificadoUnidade> lista = repository.findAll();
		if (lista.isEmpty()) {
			return Collections.emptyList();
		}
		return toBuilder.buildAll(lista);
	}

	public CertificadoUnidade getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<CertificadoUnidade> getByUnidade(Unidade unidade) {
		return repository.findByUnidade(unidade).orElse(new ArrayList<CertificadoUnidade>());
	}

	public CertificadoUnidadeTO getTOById(Long id) {
		CertificadoUnidade entity = repository.findById(id).orElseThrow(
				() -> new NotFoundException("Estrutura Unidade não encontrada."));
		return toBuilder.buildTO(entity);
	}
	
	
}
