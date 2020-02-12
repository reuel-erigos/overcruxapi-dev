package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CotacoesMaterial;

@Repository
public interface CotacoesMaterialRepository extends JpaRepository<CotacoesMaterial, Long> {

	@Query(value = "select c from CotacoesMaterial c" 
	+ " inner join Material m on m = c.material " + "where m.id = ?1")
	public Optional<List<CotacoesMaterial>> findByIdMaterial(Long idMaterial);

}
