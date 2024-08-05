package br.com.crux.dao.repository;

import br.com.crux.entity.MetaObjetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaObjetivoRepository extends JpaRepository<MetaObjetivo, Long>, JpaSpecificationExecutor<MetaObjetivo>
{

    public void deleteByObjetivoContratoId(Long id);
}


