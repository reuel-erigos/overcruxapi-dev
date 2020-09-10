
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasCentrosCusto;

@Repository
public interface ContasCentrosCustoRepository extends JpaRepository<ContasCentrosCusto, Long> {

	public Optional<List<ContasCentrosCusto>> findByIdPrograma(Long idPrograma);

	public Optional<List<ContasCentrosCusto>> findByIdProjeto(Long idProjeto);

}
