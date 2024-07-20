package br.com.crux.dao.repository;

import br.com.crux.entity.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoContratoRepository extends JpaRepository<TipoContrato, Long>{

	@Query(value = "SELECT t FROM TipoContrato t "
			+ " INNER JOIN Instituicao ins on ins.id = t.idInstituicao "
			+ " WHERE ins.id = :idInstituicao "
			+ " ORDER BY t.descricao ")
	public Optional<List<TipoContrato>> findByIdInstituicao(Long idInstituicao);

	//find by descricao

	@Query(value = "SELECT t FROM TipoContrato t "
			+ " INNER JOIN Instituicao ins on ins.id = t.idInstituicao "
			+ " WHERE ins.id = :idInstituicao "
			+ " AND lower(t.descricao) like :descricao "
			+ " ORDER BY t.descricao ")
	public Optional<List<TipoContrato>> findByIdInstituicaoAndDescricao(Long idInstituicao, String descricao);

}

