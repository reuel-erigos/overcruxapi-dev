package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CertificadoUnidade;
import br.com.crux.entity.Unidade;

@Repository
public interface CertificadoUnidadeRepository
		extends
			JpaRepository<CertificadoUnidade, Long> {

	Optional<List<CertificadoUnidade>> findByUnidade(Unidade unidade);

}
