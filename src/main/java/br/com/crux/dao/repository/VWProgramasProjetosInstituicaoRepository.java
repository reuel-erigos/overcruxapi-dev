package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.view.ProgramasProjetosInstituicao;

@Repository
public interface VWProgramasProjetosInstituicaoRepository extends JpaRepository<ProgramasProjetosInstituicao, Long>{

	@Query("SELECT vw FROM ProgramasProjetosInstituicao vw "
			+ " WHERE vw.idInstituicao = ?1")
	public Optional<List<ProgramasProjetosInstituicao>> getAllPorIdInstituicao(Long idInstituicao);
	
	
}
