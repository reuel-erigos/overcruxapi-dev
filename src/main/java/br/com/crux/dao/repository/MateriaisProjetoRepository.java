package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;

@Repository
public interface MateriaisProjetoRepository extends JpaRepository<MateriaisProjeto, Long> {

	public Optional<List<MateriaisProjeto>> findByProjeto(Projeto projeto);

	public Optional<List<MateriaisProjeto>> findByParceriasProjeto(ParceriasProjeto parceriasProjeto);


}
