package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Atendimentos;

@Repository
public interface AtendimentosRepository extends JpaRepository<Atendimentos, Long>{
	
	
	@Query(value = "SELECT a FROM Atendimentos a "
			+ " inner join Aluno aluno on aluno = a.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Atendimentos>> findByUnidade(Long idUnidade);

	
}
