package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.EstruturaUnidade;
import br.com.crux.entity.Unidade;

@Repository
public interface EstruturaUnidadeRepository
		extends
			JpaRepository<EstruturaUnidade, Long> {

	Optional<List<EstruturaUnidade>> findByUnidade(Unidade unidade);

}
