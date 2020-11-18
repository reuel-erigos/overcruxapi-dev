
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;

@Repository
public interface ContasCentrosCustoRepository extends JpaRepository<ContasCentrosCusto, Long> {

	public Optional<List<ContasCentrosCusto>> findByParceriasPrograma(ParceriasPrograma parceriasPrograma);

	public Optional<List<ContasCentrosCusto>> findByParceriasProjeto(ParceriasProjeto parceriasProjeto);

}
