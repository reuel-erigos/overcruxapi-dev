package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Provisoes;

@Repository
public interface ProvisoesRepository extends JpaRepository<Provisoes, Long>{

	@Query("select p from Provisoes p       "
			+ " where UPPER(p.status) = 'V' "
			+ " order by p.dataProvisao desc ")
	public Optional<List<Provisoes>> findAllInconsistentes();
	
}
