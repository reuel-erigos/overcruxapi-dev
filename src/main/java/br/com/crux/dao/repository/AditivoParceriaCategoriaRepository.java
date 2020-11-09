package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AditivoParceriaCategoria;
import br.com.crux.entity.ParceriasCategorias;

@Repository
public interface AditivoParceriaCategoriaRepository extends JpaRepository<AditivoParceriaCategoria, Long> {

	public Optional<List<AditivoParceriaCategoria>> findByParceriaCategoria(ParceriasCategorias parceriasCategorias);

}
