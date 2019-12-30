package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Programa;

@Repository
public interface ColaboradoresProgramaRepository extends JpaRepository<ColaboradoresPrograma, Long> {

	public Optional<List<ColaboradoresPrograma>> findByPrograma(Programa programa);

}
