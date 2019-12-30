package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ComposicaoRhProjeto;
import br.com.crux.entity.Projeto;

@Repository
public interface ComposicaoRhProjetoRepository extends JpaRepository<ComposicaoRhProjeto, Long> {

	public Optional<List<ComposicaoRhProjeto>> findByProjeto(Projeto projeto);

}
