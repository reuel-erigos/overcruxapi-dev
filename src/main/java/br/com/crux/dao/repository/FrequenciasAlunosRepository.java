package br.com.crux.dao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.FrequenciasAlunos;

@Repository
public interface FrequenciasAlunosRepository extends JpaRepository<FrequenciasAlunos, Long>{

	
	@Query(value = "SELECT f FROM FrequenciasAlunos f "
			+ " inner join AtividadesAluno ati on ati = f.atividadesAluno"
			+ " where f.id = ?1")
	public Optional<List<FrequenciasAlunos>> findAllByAtividadeAluno(Long idAtividadeAluno);	

	
	@Query(value = "SELECT f FROM FrequenciasAlunos f "
			+ " inner join AtividadesAluno ati on ati = f.atividadesAluno"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<FrequenciasAlunos>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT f FROM FrequenciasAlunos f "
			+ " inner join AtividadesAluno ati on ati = f.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where a.id = ?1")
	public Optional<List<FrequenciasAlunos>> findByAtividade(Long idAtividade);
	
	@Query(value = "SELECT f FROM FrequenciasAlunos f "
			+ " inner join AtividadesAluno ati on ati = f.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where a.id = ?1            "
			+ "   and f.dataFrequencia = ?2")
	public Optional<List<FrequenciasAlunos>> findByAtividadeAndData(Long idAtividade, LocalDate data);	
}
