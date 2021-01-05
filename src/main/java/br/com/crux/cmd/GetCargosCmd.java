package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CargosTOBuilder;
import br.com.crux.dao.repository.CargoRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CargoTO;

@Component
public class GetCargosCmd {

	@Autowired private CargoRepository repository;
	@Autowired private CargosTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<CargoTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Cargo>> cargos = repository.findByIdInstituicao(idInstituicao);
		if(cargos.isPresent()) {
			List<CargoTO> entitys = toBuilder.buildAll(cargos.get());
			return entitys;
		}
		
		return new ArrayList<CargoTO>();
	}

	public CargoTO getTOById(Long id) {
		Cargo entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Cargo não encontrado"));
		return toBuilder.buildTO(entity);
	}

	public Cargo getById(Long id) {
		return repository.findById(id).orElse(null);

	}

}
