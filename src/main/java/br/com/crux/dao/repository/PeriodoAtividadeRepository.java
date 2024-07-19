package br.com.crux.dao.repository;

import br.com.crux.entity.PeriodoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodoAtividadeRepository extends JpaRepository<PeriodoAtividade, Long>
{

    @Query(value = "SELECT e " +
                   "	FROM PeriodoAtividade e " +
                   " 	INNER JOIN Instituicao ins on ins.id = e.idInstituicao " +
                   " WHERE ins.id = :idInstituicao " +
                   " ORDER BY e.nome ")
    public Optional<List<PeriodoAtividade>> findByIdInstituicao(Long idInstituicao);

    @Query(value = "SELECT e " +
                   " FROM PeriodoAtividade e " +
                   " 	INNER JOIN Instituicao ins on ins.id = e.idInstituicao " +
                   " WHERE 1 = 1 " +
                   " 	AND ins.id = ?1 " +
                   "   	AND (?2 IS NULL OR LOWER(e.nome) LIKE ?2) " +
                   "   	AND (?3 IS NULL OR LOWER(e.descricao) LIKE ?3) " +
                   " ORDER BY e.nome DESC ")
    public Optional<List<PeriodoAtividade>> findByFilters(Long idInstiuicao, String nome, String descricao);

}

