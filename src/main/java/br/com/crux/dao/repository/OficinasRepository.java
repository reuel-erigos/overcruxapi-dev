package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Oficinas;

@Repository
public interface OficinasRepository extends JpaRepository<Oficinas, Long>{
	
	
	@Query(value = "SELECT ati FROM Oficinas ati"
			+ " inner join Unidade uni on ati.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Oficinas>> findByIdUnidade(Long idUnidade);

	@Query(value = "SELECT ati FROM Oficinas ati"
			+ " inner join Unidade uni on ati.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Oficinas>> findAllUnidadeDoPrograma(Long idUnidade);
	

	@Query(value = "SELECT ati FROM Oficinas ati"
			     + " where ati.idTurma = ?1")	
	public Optional<List<Oficinas>> findByIdTurma(Long idTurma);
	
}
