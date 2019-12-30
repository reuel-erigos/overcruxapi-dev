package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Metas;

@Repository
public interface MetasRepository extends JpaRepository<Metas, Long>{

	@Query(value = "SELECT m FROM Metas m "
			+ " inner join Indicadores i on m.indicadores = i"
			+ " inner join Objetivo o on i.objetivo = o"
			+ " inner join Perspectiva p on o.perspectiva = p "
			+ " inner join Unidade u on p.unidade = u "
			+ " where u.idUnidade = ?1")
	public Optional<List<Metas>> findByIdUnidade(Long idUnidade);

}
