package br.com.crux.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GrupoAcoes;

@Repository
public interface GrupoAcoesRepository extends JpaRepository<GrupoAcoes, Long>{
	
	@Query(value = "SELECT ga FROM GrupoAcoes ga "
			+ " where 1 = 1 "
			+ "   and ga.numeroGrupo = ?1 ")
	public Optional<GrupoAcoes> findByNumero(String numero);
	
}
