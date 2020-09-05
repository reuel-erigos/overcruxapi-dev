package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CategoriasContabeis;

@Repository
public interface CategoriasContabeisRepository extends JpaRepository<CategoriasContabeis, Long> {
	
	@Query("select c from CategoriasContabeis c "
			+ " inner join Instituicao inst on inst.id = c.idInstituicao "
			+ "where inst.id = ?1 "
			+ " order by c.nome ")
	public Optional<List<CategoriasContabeis>> findAllByInstituicao(Long idInstituicao);

}
