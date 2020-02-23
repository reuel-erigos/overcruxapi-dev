package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;

@Repository
public interface ColaboradoresProjetoRepository extends JpaRepository<ColaboradoresProjeto, Long> {

	public Optional<List<ColaboradoresProjeto>> findByProjeto(Projeto projeto);

}
