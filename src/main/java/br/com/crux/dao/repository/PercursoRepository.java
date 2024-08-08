package br.com.crux.dao.repository;

import br.com.crux.entity.Percurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PercursoRepository extends JpaRepository<Percurso, Long>, JpaSpecificationExecutor<Percurso>
{

	@Query(value = "SELECT c FROM Percurso c "
			+ " INNER JOIN Instituicao ins ON ins.id = c.idInstituicao "
			+ " WHERE ins.id = :idInstituicao "
			+ " ORDER BY c.nome ")
	public Optional<List<Percurso>> findByIdInstituicao(Long idInstituicao);

}

