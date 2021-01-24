package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	@Query(value = "select c from Cargo c "
			+ " inner join Instituicao i on i.id = c.idInstituicao "
			+ " where i.id = ?1 "
			+ " order by c.nome ")
	public Optional<List<Cargo>> findByIdInstituicao(Long idInstituicao);
}

