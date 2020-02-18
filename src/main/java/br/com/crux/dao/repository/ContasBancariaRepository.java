package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ContasBancaria;

@Repository
public interface ContasBancariaRepository extends JpaRepository<ContasBancaria, Long> {

	@Query(value = "select f from ContasBancaria f" + " inner join Unidade u on u = f.unidade " + "where u.idUnidade = ?1")
	public Optional<List<ContasBancaria>> findByIdUnidade(Long idUnidade);

	
	@Query(value = "select f from ContasBancaria f" 
	             + " inner join Unidade u on u = f.unidade "
	             + " inner join Instituicao i on i = u.instituicao" 
			      + "where i.id = ?1")
	public Optional<List<ContasBancaria>> findByIdInstituicao(Long idInstituicao);
	
}
