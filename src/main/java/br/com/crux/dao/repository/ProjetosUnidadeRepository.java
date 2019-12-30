package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Projeto;
import br.com.crux.entity.ProjetosUnidade;

@Repository
public interface ProjetosUnidadeRepository extends JpaRepository<ProjetosUnidade, Long> {

	public Optional<List<ProjetosUnidade>> findByProjeto(Projeto projeto);

}
