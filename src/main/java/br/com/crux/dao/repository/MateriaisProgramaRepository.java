package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;

@Repository
public interface MateriaisProgramaRepository extends JpaRepository<MateriaisPrograma, Long> {

	public Optional<List<MateriaisPrograma>> findByPrograma(Programa programa);

	public Optional<List<MateriaisPrograma>> findByParceriasPrograma(ParceriasPrograma parceriasPrograma);
}
