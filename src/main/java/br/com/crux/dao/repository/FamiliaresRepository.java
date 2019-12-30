package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Familiares;

@Repository
public interface FamiliaresRepository extends JpaRepository<Familiares, Long>{
	
	
	@Query(value = "SELECT f FROM Familiares f "
			+ " inner join Aluno aluno on aluno = f.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Familiares>> findByUnidade(Long idUnidade);


	@Query(value = "SELECT f FROM Familiares f "
			+ " inner join Aluno aluno on aluno = f.aluno"
			+ " where aluno.id = ?1")
	public Optional<List<Familiares>> findByFamiliaresPorAluno(Long idAluno);
	
}
