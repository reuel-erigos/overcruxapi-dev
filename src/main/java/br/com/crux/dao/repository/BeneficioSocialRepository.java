package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.BeneficioSocial;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficioSocialRepository extends JpaRepository<BeneficioSocial, Long>{


    @Query(value = "SELECT bs FROM BeneficioSocial bs "
            + " where bs.id = ?1")
    public Optional<List<BeneficioSocial>> findByBeneficilSocial(Long idBeneficilSocial);

}
