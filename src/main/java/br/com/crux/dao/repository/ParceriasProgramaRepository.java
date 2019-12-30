package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;

@Repository
public interface ParceriasProgramaRepository extends JpaRepository<ParceriasPrograma, Long> {

	public Optional<List<ParceriasPrograma>> findByPrograma(Programa programa);

}
