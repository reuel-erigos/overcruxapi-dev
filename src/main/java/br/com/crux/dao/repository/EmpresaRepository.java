package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Query("select e from Empresa e                                     "
			+ "inner join Instituicao inst on inst.id = e.idInstituicao "
			+ " where inst.id = ?1                                      "
			+ " order by e.nomeRazaoSocial asc                          ")
	public Optional<List<Empresa>> getAllByInstituicao(Long idInstituicao);

}
