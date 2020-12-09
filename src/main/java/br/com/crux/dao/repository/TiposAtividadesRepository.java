package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.TiposAtividades;

@Repository
public interface TiposAtividadesRepository
		extends
			JpaRepository<TiposAtividades, Long> {

}
