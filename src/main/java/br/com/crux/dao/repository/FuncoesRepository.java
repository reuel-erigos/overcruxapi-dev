package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Funcoes;

@Repository
public interface FuncoesRepository extends JpaRepository<Funcoes, Long> {

	@Query(value = "select f from Funcoes f "
			     + " inner join FuncoesInstituicao fi on fi.funcao = f " 
	             + " inner join Instituicao i on i = fi.instituicao " 
			     + " where i.id = ?1 "
			     + " order by f.nome ")
	public Optional<List<Funcoes>> findByIdInstituicao(Long idInstituicao);

}
