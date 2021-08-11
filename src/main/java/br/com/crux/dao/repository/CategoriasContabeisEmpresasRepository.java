package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.CategoriasContabeisEmpresas;

@Repository
public interface CategoriasContabeisEmpresasRepository extends JpaRepository<CategoriasContabeisEmpresas, Long> {

	@Query(value = "select cce from CategoriasContabeisEmpresas cce                     "
			+ " inner join Empresa e on e.id = cce.idEmpresa                            "
			+ " where cce.id = ?1                                                       ")
	public Optional<List<CategoriasContabeisEmpresas>> findByIdEmpresa(Long idEmpresa);
	

}
