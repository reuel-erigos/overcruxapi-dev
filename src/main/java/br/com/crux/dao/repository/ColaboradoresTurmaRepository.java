package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ColaboradoresTurma;

@Repository
public interface ColaboradoresTurmaRepository extends JpaRepository<ColaboradoresTurma, Long> {

	public Optional<List<ColaboradoresTurma>> findByIdTurma(Long idTurma);

}
