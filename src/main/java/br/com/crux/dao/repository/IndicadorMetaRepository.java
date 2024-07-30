package br.com.crux.dao.repository;

import br.com.crux.entity.IndicadorMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorMetaRepository extends JpaRepository<IndicadorMeta, Long>, JpaSpecificationExecutor<IndicadorMeta>
{

}

