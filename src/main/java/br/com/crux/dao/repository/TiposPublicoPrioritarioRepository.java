package br.com.crux.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TiposPublicoPrioritario;

@Repository
public interface TiposPublicoPrioritarioRepository
		extends
			JpaRepository<TiposPublicoPrioritario, Long> {
	
	@Query(value = "SELECT md FROM TiposPublicoPrioritario md "
			+ " inner join Instituicao i on md.idInstituicao = i.id"
			+ " where i.id = ?1")
	public List<TiposPublicoPrioritario> findByInstituicao(Long idInstituicao);

}
