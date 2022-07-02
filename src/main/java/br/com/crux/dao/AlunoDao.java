package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ComboAlunoDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.infra.util.NumeroUtil;
import br.com.crux.to.AlunoTO;

@Component
public class AlunoDao extends BaseDao {

	public List<ComboAlunoDTO> getAllByInstituicao(Long idInstituicao) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.id_aluno,                                                           ");
		sql.append("        pf.nm_pessoa_fisica,                                                  ");
		sql.append("        a.nr_matricula_aluno,                                                 ");
		sql.append("        a.dt_entrada,                                                         ");
		sql.append("        a.dt_desligamento                                                     ");
		sql.append("  from alunos a                                                               ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append(" where 1=1                                                                    ");
		sql.append("  and pf.id_instituicao = :idInstituicao                                      ");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);

		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();

		List<ComboAlunoDTO> retorno = new ArrayList<ComboAlunoDTO>();
		values.stream().forEach(colunas -> retorno.add(new ComboAlunoDTO(colunas)));

		return retorno;

	}

	public Optional<List<AlunoTO>> getAllFilter(Long idInstituicao, Long idAluno,
			String idPessoaFisicaMae, String cpfPessoaFisicaAluno, LocalDate dataInicioEntradaInstituicao,
			LocalDate dataFimEntradaInstituicao) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select p.id_pessoa_fisica,");
		sql.append("        a.id_aluno,");
		sql.append("        a.nr_matricula_aluno,");
		sql.append("        p.nm_pessoa_fisica,");
		sql.append("        a.dt_entrada,");
		sql.append("        a.dt_desligamento,");
		sql.append("        a.st_ativo,");
		sql.append("        a.st_apr_externa_pub,");
		sql.append("        a.st_mora_pais,");
		sql.append("        a.st_pais_casados");
		sql.append("    from pessoas_fisicas p");
		sql.append("      inner join alunos a on p.id_pessoa_fisica = a.id_pessoa_fisica");
		sql.append(" WHERE 1 = 1");
		sql.append("  and p.id_instituicao = :idInstituicao");
		if (StringUtils.isNotEmpty(cpfPessoaFisicaAluno)) {
			sql.append("  and :p_nr_cpf = p.nr_cpf");
		}
		if (Objects.nonNull(dataInicioEntradaInstituicao)) {
			sql.append("   AND DATE_TRUNC('DAY', a.dt_entrada) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_entrada ,'dd/mm/yyyy') )");
		}
		if (Objects.nonNull(dataFimEntradaInstituicao)) {
			sql.append("   AND DATE_TRUNC('DAY', a.dt_desligamento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_entrada ,'dd/mm/yyyy') )");
		}
		if (Objects.nonNull(idAluno)) {
			sql.append("  and :p_id_aluno = a.id_aluno");
		}
		if (Objects.nonNull(idPessoaFisicaMae)) {
			sql.append("  and :p_nm_mae = p.nm_mae");
		}
		sql.append(" order by p.nm_pessoa_fisica, p.nm_mae  ");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);

		if (StringUtils.isNotEmpty(cpfPessoaFisicaAluno)) {
			query.setParameter("p_nr_cpf", cpfPessoaFisicaAluno.replace(".","").replace("-",""));
		}

		if (Objects.nonNull(dataInicioEntradaInstituicao)) {
			query.setParameter("p_dt_inicio_entrada", Java8DateUtil.getLocalDateFormater(dataInicioEntradaInstituicao));
		}

		if (Objects.nonNull(dataFimEntradaInstituicao)) {
			query.setParameter("p_dt_fim_entrada", Java8DateUtil.getLocalDateFormater(dataFimEntradaInstituicao));
		}

		if (Objects.nonNull(idPessoaFisicaMae)) {
			query.setParameter("p_nm_mae", idPessoaFisicaMae);
		}
		
		if (Objects.nonNull(idAluno)) {
			query.setParameter("p_id_aluno", idAluno);
		}

		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();

		List<AlunoTO> retorno = new ArrayList<AlunoTO>();
		values.stream().forEach(colunas -> retorno.add(new AlunoTO(colunas)));

		return Optional.ofNullable(retorno);
	}

}
