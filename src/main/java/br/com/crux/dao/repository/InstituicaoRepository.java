package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{
	

}
