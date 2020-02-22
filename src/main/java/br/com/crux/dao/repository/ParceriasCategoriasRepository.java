
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;

@Repository
public interface ParceriasCategoriasRepository extends JpaRepository<ParceriasCategorias, Long> {

	public Optional<List<ParceriasCategorias>> findByParceriasPrograma(ParceriasPrograma parceriasPrograma);

	public Optional<List<ParceriasCategorias>> findByParceriasProjeto(ParceriasProjeto parceriasProjeto);

}
