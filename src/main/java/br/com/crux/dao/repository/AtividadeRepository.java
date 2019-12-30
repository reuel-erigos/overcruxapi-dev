package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Atividades;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividades, Long>{
	
	
	@Query(value = "SELECT ati FROM Atividades ati"
			+ " inner join Unidade uni on ati.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Atividades>> findByIdUnidade(Long idUnidade);

	@Query(value = "SELECT ati FROM Atividades ati"
			+ " inner join Unidade uni on ati.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Atividades>> findAllUnidadeDoPrograma(Long idUnidade);
	

	@Query(value = "SELECT ati FROM Atividades ati"
			     + " where ati.idTurma = ?1")	
	public Optional<List<Atividades>> findByIdTurma(Long idTurma);
	
}
