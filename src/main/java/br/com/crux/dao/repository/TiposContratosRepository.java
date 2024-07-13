package br.com.crux.dao.repository;

import br.com.crux.entity.TiposContratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiposContratosRepository extends JpaRepository<TiposContratos, Long>{

	@Query(value = "SELECT t FROM TiposContratos t                      "
			+ " INNER JOIN Instituicao ins on ins.id = t.idInstituicao "
			+ " WHERE ins.id = :idInstituicao                          "
			+ " ORDER BY t.descricao                                   ")
	public Optional<List<TiposContratos>> findByIdInstituicao(Long idInstituicao);

}

