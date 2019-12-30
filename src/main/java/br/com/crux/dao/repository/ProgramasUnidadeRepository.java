package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Programa;
import br.com.crux.entity.ProgramasUnidade;

@Repository
public interface ProgramasUnidadeRepository extends JpaRepository<ProgramasUnidade, Long> {

	public Optional<List<ProgramasUnidade>> findByPrograma(Programa programa);

}
