package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long>{

	@Query(value = "SELECT o FROM Objetivo o "
			+ " inner join Perspectiva p on o.perspectiva = p"
			+ " inner join Unidade u on p.unidade = u"
			+ " where u.idUnidade = ?1")
	public Optional<List<Objetivo>> findByIdUnidade(Long idUnidade);

}
