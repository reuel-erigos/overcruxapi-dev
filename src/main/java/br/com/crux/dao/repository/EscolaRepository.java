package br.com.crux.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long>, JpaSpecificationExecutor<Escola>{
	
	
	public List<Escola> findByInstituicaoIdAndTipoOrderByNome(Long idInstituicao, String tipo);


	
}
