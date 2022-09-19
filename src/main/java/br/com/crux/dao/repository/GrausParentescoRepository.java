package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GrausParentesco;

@Repository
public interface GrausParentescoRepository extends JpaRepository<GrausParentesco, Long>{
	
	

	
}
