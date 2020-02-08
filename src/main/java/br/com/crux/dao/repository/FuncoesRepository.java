package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Funcoes;

@Repository
public interface FuncoesRepository extends JpaRepository<Funcoes, Long> {

	@Query(value = "select f from Funcoes f" + 
	" inner join Unidade u on u = f.unidade " + "where u.idUnidade = ?1")
	public Optional<List<Funcoes>> findByIdUnidade(Long idUnidade);

}
