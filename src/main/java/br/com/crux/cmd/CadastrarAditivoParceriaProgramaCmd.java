package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaProgramaTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaProgramaRepository;
import br.com.crux.entity.AditivoParceriaPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.AditivoParceriaProgramaTO;

@Component
public class CadastrarAditivoParceriaProgramaCmd {

	@Autowired
	AditivoParceriaProgramaRepository repository;
	@Autowired
	AditivoParceriaProgramaTOBuilder builder;

	public AditivoParceriaPrograma cadastrar(ParceriasPrograma p, AditivoParceriaProgramaTO to) {
		AditivoParceriaPrograma entity = builder.build(p, to);
		return repository.save(entity);
	}

	public List<AditivoParceriaPrograma> cadastrarLista(ParceriasPrograma programa,
			List<AditivoParceriaProgramaTO> parceriasPrograma) {
		return parceriasPrograma.stream().map(parceriaPrograma -> cadastrar(programa, parceriaPrograma))
				.collect(Collectors.toList());

	}

}
