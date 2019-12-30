package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.EncaminhaAlunos;

@Repository
public interface EncaminhaAlunosRepository extends JpaRepository<EncaminhaAlunos, Long>{
	
	
	@Query(value = "SELECT e FROM EncaminhaAlunos e "
			+ " inner join Aluno a on a = e.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<EncaminhaAlunos>> findByUnidade(Long idUnidade);

	@Query(value = "SELECT e FROM EncaminhaAlunos e "
			+ " inner join EntidadesSociais es on es = e.entidadesSocial"
			+ " inner join Aluno a on a = e.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1"
			+ "   and a.id          = ?2")
	public Optional<List<EncaminhaAlunos>> findByUnidadeAluno(Long idUnidade, Long idAluno);

	@Query(value = "SELECT e FROM EncaminhaAlunos e "
			+ " inner join EntidadesSociais es on es = e.entidadesSocial"
			+ " inner join Aluno a on a = e.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where es.id         = ?1")
	public Optional<List<EncaminhaAlunos>> findByEntidadeSocial(Long idEntidadeSocial);

	
	@Query(value = "SELECT e FROM EncaminhaAlunos e "
			+ " inner join EntidadesSociais es on es = e.entidadesSocial"
			+ " inner join Aluno a on a = e.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1"
			+ "   and a.id          = ?2"
			+ "   and es.id         = ?3")
	public Optional<List<EncaminhaAlunos>> findByUnidadeAlunoEntidadeSocial(Long idUnidade, Long idAluno, Long idEntidadeSocial);

	
}
