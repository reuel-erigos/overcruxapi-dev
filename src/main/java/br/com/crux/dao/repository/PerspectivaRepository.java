package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Perspectiva;

@Repository
public interface PerspectivaRepository extends JpaRepository<Perspectiva, Long>{

	@Query(value = "select p from Perspectiva p "
			+ " inner join Unidade u on u = p.unidade "
			+ " where u.idUnidade = ?1")
	public Optional<List<Perspectiva>> findByIdUnidade(Long idUnidade);
	

}
