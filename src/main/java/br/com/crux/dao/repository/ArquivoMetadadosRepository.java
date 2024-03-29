package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ArquivoMetadado;

@Repository
public interface ArquivoMetadadosRepository extends JpaRepository<ArquivoMetadado, Long>, JpaSpecificationExecutor<ArquivoMetadado> {

	ArquivoMetadado findByIdInstituicaoAndTipo(Long idInstituicao, String tipo);
}
