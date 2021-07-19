package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.MovimentacoesContabeis;

@Repository
public interface MovimentacoesContabeisRepository extends JpaRepository<MovimentacoesContabeis, Long> {

	
	@Query(value = "select m from MovimentacoesContabeis m         " 
	             + " inner join Instituicao i on i.id = m.idInstituicao " 
			      + " where i.id = ?1                              "
			      + " order by m.dataMovimentacao                  ")
	public Optional<List<MovimentacoesContabeis>> findByIdInstituicao(Long idInstituicao);
	
	
}
