package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RegiaoAdministrativa;

@Repository
public interface RegiaoAdministrativaRepository extends JpaRepository<RegiaoAdministrativa, Long>, JpaSpecificationExecutor<RegiaoAdministrativa>{
	
}
