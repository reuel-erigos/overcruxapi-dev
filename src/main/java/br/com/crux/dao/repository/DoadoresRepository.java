package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Doadores;

@Repository
public interface DoadoresRepository extends JpaRepository<Doadores, Long>{

	@Query(value = "SELECT d FROM Doadores d                           "
			+ " inner join Instituicao ins on ins.id = d.idInstituicao "
			+ " inner join Empresa emp on emp = d.empresa              "
			+ " where ins.id = :idInstituicao                          "
			+ " order by emp.nomeRazaoSocial                           ")
	public Optional<List<Doadores>> findByIdInstituicao(Long idInstituicao);
	

}

