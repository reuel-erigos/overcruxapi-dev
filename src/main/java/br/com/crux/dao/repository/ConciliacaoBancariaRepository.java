package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ConciliacaoBancaria;

@Repository
public interface ConciliacaoBancariaRepository extends JpaRepository<ConciliacaoBancaria, Long>{

	@Query("select p from ConciliacaoBancaria c       "
			+ " where UPPER(c.status) = 'V'           "
			+ " order by c.dataConciliacao asc        ")
	public Optional<List<ConciliacaoBancaria>> findAllInconsistentes();
	
}
