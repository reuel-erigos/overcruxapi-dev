package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Indicadores;

@Repository
public interface IndicadoresRepository extends JpaRepository<Indicadores, Long>{

	@Query(value = "SELECT i FROM Indicadores i "
			+ " inner join Objetivo o on i.objetivo = o"
			+ " inner join Perspectiva p on o.perspectiva = p "
			+ " inner join Unidade u on p.unidade = u "
			+ " where p.unidade.idUnidade = ?1")
	public Optional<List<Indicadores>> findByIdUnidade(Long idUnidade);

}
