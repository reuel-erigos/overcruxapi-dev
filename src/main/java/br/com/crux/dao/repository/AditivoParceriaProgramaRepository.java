package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AditivoParceriaPrograma;
import br.com.crux.entity.ParceriasPrograma;

@Repository
public interface AditivoParceriaProgramaRepository extends JpaRepository<AditivoParceriaPrograma, Long> {

	public Optional<List<AditivoParceriaPrograma>> findByParceriasPrograma(ParceriasPrograma parceriasPrograma);

}
