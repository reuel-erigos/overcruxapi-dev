package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Departamentos;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamentos, Long>{
	

	@Query(value = "select d from Departamentos d"
			+ " inner join Unidade u on u = d.unidade "
			+ " where u.idUnidade = ?1"
			+ " order by d.nmDepartamento")
	public Optional<List<Departamentos>> findByIdUnidade(Long idUnidade);
	
	@Query(value = "select d from Departamentos d"
			+ " inner join Unidade u on u = d.unidade "
			+ " inner join Instituicao i on i = u.instituicao "
			+ "where i.id = ?1 "
			+ " order by d.nmDepartamento")
	public Optional<List<Departamentos>> findByIdInstituicao(Long idInstituicao);
	
}
