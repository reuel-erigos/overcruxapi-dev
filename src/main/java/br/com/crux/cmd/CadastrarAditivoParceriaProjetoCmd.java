package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaProjetoTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaProjetoRepository;
import br.com.crux.entity.AditivoParceriaProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.AditivoParceriaProjetoTO;

@Component
public class CadastrarAditivoParceriaProjetoCmd {

	@Autowired
	AditivoParceriaProjetoRepository repository;
	@Autowired
	AditivoParceriaProjetoTOBuilder builder;

	public AditivoParceriaProjeto cadastrar(ParceriasProjeto p, AditivoParceriaProjetoTO to) {
		AditivoParceriaProjeto entity = builder.build(p, to);
		return repository.save(entity);
	}

	public List<AditivoParceriaProjeto> cadastrarLista(ParceriasProjeto projeto,
			List<AditivoParceriaProjetoTO> ParceriasProjeto) {
		return ParceriasProjeto.stream().map(parceriaPrograma -> cadastrar(projeto, parceriaPrograma))
				.collect(Collectors.toList());

	}

}
