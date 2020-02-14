package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Movimentacoes;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Long> {

	@Query(value = "select f from Movimentacoes f" + " inner join Unidade u on u = f.unidade " + "where u.idUnidade = ?1")
	public Optional<List<Movimentacoes>> findByIdUnidade(Long idUnidade);

}
