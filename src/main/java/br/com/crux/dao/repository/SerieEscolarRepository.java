package br.com.crux.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.SerieEscolar;

@Repository
public interface SerieEscolarRepository extends JpaRepository<SerieEscolar, Long>, JpaSpecificationExecutor<SerieEscolar>{
	
}
