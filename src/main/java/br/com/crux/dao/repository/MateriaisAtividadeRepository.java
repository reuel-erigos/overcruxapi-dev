package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MateriaisAtividade;

@Repository
public interface MateriaisAtividadeRepository extends JpaRepository<MateriaisAtividade, Long> {

	@Query(value = "SELECT p FROM MateriaisAtividade p " 
			+ " where p.idAtividade = ?1")
	public Optional<List<MateriaisAtividade>> getPorAtividade(Long idAtividade);

}
