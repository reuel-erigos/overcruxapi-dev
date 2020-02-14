package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;

@Repository
public interface ContasCentrosCustoRepository extends JpaRepository<ContasCentrosCusto, Long> {

	public Optional<List<ContasCentrosCusto>> findByPrograma(Programa programa);

	public Optional<List<ContasCentrosCusto>> findByProjeto(Projeto projeto);

}
