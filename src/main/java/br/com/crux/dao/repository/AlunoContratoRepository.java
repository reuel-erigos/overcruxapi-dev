package br.com.crux.dao.repository;

import br.com.crux.entity.AlunoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoContratoRepository extends JpaRepository<AlunoContrato, Long>, JpaSpecificationExecutor<AlunoContrato>
{

}

