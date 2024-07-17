package br.com.crux.dao.repository;

import br.com.crux.entity.ProgramaContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaContratoRepository extends JpaRepository<ProgramaContrato, Long>
{

    public void deleteByContratoId(Long id);

}

