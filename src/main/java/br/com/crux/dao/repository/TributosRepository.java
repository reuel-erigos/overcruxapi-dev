package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Tributos;

@Repository
public interface TributosRepository extends JpaRepository<Tributos, Long>{


	@Query(value = "SELECT t FROM Tributos t "
			+ " inner join Instituicao ins on ins.id = t.idInstituicao "
			+ " where ins.id = ?1 "
			+ " order by t.descricao ")
	public Optional<List<Tributos>> findByIdInstituicao(Long idInstituicao);	

}

