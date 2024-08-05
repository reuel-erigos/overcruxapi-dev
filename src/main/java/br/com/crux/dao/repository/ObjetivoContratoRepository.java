package br.com.crux.dao.repository;

import br.com.crux.entity.ObjetivoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoContratoRepository
        extends JpaRepository<ObjetivoContrato, Long>, JpaSpecificationExecutor<ObjetivoContrato> {

    public void deleteByContratoId(Long id);

}

