package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.view.PlanosContas;

@Repository
public interface VWPlanosContasRepository extends JpaRepository<PlanosContas, Long>{

	
	@Query("select  vw from PlanosContas vw "
			+ "where vw.idInstituicao = :idInstituicao "
			+ " order by vw.planoConta ")
	public Optional<List<PlanosContas>> findAllByInstituicao(Long idInstituicao);

}
