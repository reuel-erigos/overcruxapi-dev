package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ComposicaoRhPrograma;
import br.com.crux.entity.Programa;

@Repository
public interface ComposicaoRhProgramaRepository extends JpaRepository<ComposicaoRhPrograma, Long> {

	public Optional<List<ComposicaoRhPrograma>> findByPrograma(Programa programa);

}
