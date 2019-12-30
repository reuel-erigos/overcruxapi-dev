package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;

@Repository
public interface ParceriasProjetoRepository extends JpaRepository<ParceriasProjeto, Long> {

	public Optional<List<ParceriasProjeto>> findByProjeto(Projeto projeto);

}
