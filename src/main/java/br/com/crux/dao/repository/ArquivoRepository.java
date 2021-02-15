package br.com.crux.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

	@Query(value = "SELECT a FROM Arquivo a                             "
			+ " inner join ArquivoMetadado m on m = a.metadados         "
			+ " inner join PessoaFisica pf on pf.metadados = m          "
			+ " where pf.id = ?1")
	public Optional<Arquivo> findByIdPessoaFisica(Long idPessoaFisica);
	
	
	@Query(value = "SELECT a FROM Arquivo a                       "
			+ " inner join ArquivoMetadado m on m = a.metadados   "
			+ " inner join Unidade u on u.arquivoMetadados = m    "
			+ " where u.id = ?1                                   ")
	public Optional<Arquivo> findByIdUnidade(Long idUnidade);
	
	
	@Query(value = "SELECT a FROM Arquivo a                       "
			+ " inner join ArquivoMetadado m on m = a.metadados   "
			+ " inner join Instituicao i on i.metadados = m       "
			+ " where i.id = ?1                                   ")
	public Optional<Arquivo> findByIdInstituicao(Long idInstituicao);
	
	@Query(value = "SELECT a FROM Arquivo a                            "
				 + " inner join ArquivoMetadado am on am = a.metadados "
				 + " inner join Instituicao i on i.metadados = am      "
				 + " where am.id = ?1                                  ")
	public Optional<Arquivo> findByIdMetadados(Long idMetadados);


}
