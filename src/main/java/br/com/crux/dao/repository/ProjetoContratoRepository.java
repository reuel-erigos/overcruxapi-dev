package br.com.crux.dao.repository;

import br.com.crux.entity.ProjetoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoContratoRepository extends JpaRepository<ProjetoContrato, Long>
{

    public void deleteByContratoId(Long id);

}

