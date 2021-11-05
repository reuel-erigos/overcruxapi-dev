package br.com.crux.builder;

import br.com.crux.dao.repository.BeneficioSocialRepository;
import br.com.crux.dao.repository.CargoRepository;
import br.com.crux.entity.BeneficioSocial;
import br.com.crux.entity.Cargo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ExcluirBeneficilSocialCmd {

	@Autowired
	private BeneficioSocialRepository repository;
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o beneficil social.");
		}
		
		Optional<BeneficioSocial> entity = repository.findById(id);
		if(!entity.isPresent()) {
			throw new NotFoundException("Beneficil social informado não existe.");
		}
		
		repository.deleteById(id);
		
	}
}
