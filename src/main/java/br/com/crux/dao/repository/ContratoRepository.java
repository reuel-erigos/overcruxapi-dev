package br.com.crux.dao.repository;

import br.com.crux.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long>, JpaSpecificationExecutor<Contrato>
{

	@Query(value = "SELECT c FROM Contrato c "
			+ " INNER JOIN Instituicao ins ON ins.id = c.idInstituicao "
			+ " WHERE ins.id = :idInstituicao "
			+ " ORDER BY c.numeroContrato ")
	public Optional<List<Contrato>> findByIdInstituicao(Long idInstituicao);

}

