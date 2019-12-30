package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.PlanosAcao;

@Repository
public interface PlanosAcaoRepository extends JpaRepository<PlanosAcao, Long>{

	@Query(value = "SELECT p FROM PlanosAcao p "
			+ " inner join Iniciativa ini on p.iniciativa = ini"
			+ " inner join Metas m on ini.meta = m"
			+ " inner join Indicadores i on m.indicadores = i"
			+ " inner join Objetivo o on i.objetivo = o"
			+ " inner join Perspectiva pe on o.perspectiva = pe "
			+ " inner join Unidade u on pe.unidade = u "
			+ " where u.idUnidade = ?1")
	public Optional<List<PlanosAcao>> findByIdUnidade(Long idUnidade);

}
