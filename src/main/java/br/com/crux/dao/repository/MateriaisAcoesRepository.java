package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MateriaisAcoes;

@Repository
public interface MateriaisAcoesRepository extends JpaRepository<MateriaisAcoes, Long> {

	@Query(value = "select m from MateriaisAcoes m "
			+ " where m.idAcao = ?1") 
	public Optional<List<MateriaisAcoes>> findAllByIdAcao(Long idAcao);

}
