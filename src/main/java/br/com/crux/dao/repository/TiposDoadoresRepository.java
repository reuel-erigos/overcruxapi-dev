package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TiposDoadores;

@Repository
public interface TiposDoadoresRepository extends JpaRepository<TiposDoadores, Long>{

	@Query(value = "SELECT t FROM TiposDoadores t                      "
			+ " inner join Instituicao ins on ins.id = t.idInstituicao "
			+ " where ins.id = :idInstituicao                          "
			+ " order by t.descricao                                   ")
	public Optional<List<TiposDoadores>> findByIdInstituicao(Long idInstituicao);

}

