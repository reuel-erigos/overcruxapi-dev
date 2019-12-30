package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ParticipantesAtendimentos;

@Repository
public interface ParticipantesAtendimentosRepository extends JpaRepository<ParticipantesAtendimentos, Long>{
	
	
	@Query(value = "SELECT a FROM ParticipantesAtendimentos a "
			+ " inner join Atendimentos at on at = a.atendimento"
			+ " inner join Aluno aluno on aluno = at.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<ParticipantesAtendimentos>> findByUnidade(Long idUnidade);

	
}
