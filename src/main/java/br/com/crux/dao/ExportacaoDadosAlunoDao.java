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
import br.com.crux.dao.dto.ExportacaoDadosAlunoDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.infra.util.NumeroUtil;

@Component
public class ExportacaoDadosAlunoDao extends BaseDao{
	
	
	public Optional<List<ExportacaoDadosAlunoDTO>> getAllFilter(String cpf, Long idBeneficiario, Long idMae, Long idPai, Long idPrograma,
														        Long idProjeto, Long idUnidade, Long idResponsavel, 
														        LocalDate dataInicioEntradaInstituicao, LocalDate dataFimEntradaInstituicao, 
														        LocalDate dataInicioVigenciaInstituicao, LocalDate dataFimVigenciaInstituicao,
														        Long idInstituicao){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select p.id_pessoa_fisica,                                                                             ");
		sql.append("        a.id_aluno,                                                                                     ");
		sql.append("        a.nr_matricula_aluno,                                                                           ");
		sql.append("        p.nm_pessoa_fisica,                                                                             ");
		sql.append("        p.dt_nascimento,                                                                                ");
		sql.append("        p.nm_mae,                                                                                       ");
		sql.append("        p.nm_pai,                                                                                       ");
        sql.append("        p.nr_cpf,                                                                                       ");
        sql.append("        p.nr_nis,                                                                                       ");
        sql.append("        p.ds_email,                                                                                     ");
        sql.append("        p.nr_fone_celular,                                                                              ");
        sql.append("        p.nr_telefone_residencial,                                                                      ");
        sql.append("        p.nr_telefone_comercial,                                                                        ");
        sql.append("        u.nm_unidade,                                                                                   ");
        sql.append("        a.dt_entrada,                                                                                   ");
        sql.append("        a.dt_desligamento                                                                               ");
		sql.append("    from pessoas_fisicas p                                                                              ");
		sql.append("      inner join alunos a on p.id_pessoa_fisica = a.id_pessoa_fisica                                    ");
		sql.append("      left join pessoas_fisicas pfm on pfm.id_pessoa_fisica = a.id_pessoa_fisica                        ");
		sql.append("      left join pessoas_fisicas pfp on pfp.id_pessoa_fisica = a.id_pessoa_fisica                        ");
		sql.append("      left join programas poa on poa.id_programa = a.id_programa                                        ");
		sql.append("      left join projetos pra on pra.id_projeto = a.id_projeto                                           ");
		sql.append("      left join alunos_turma at on at.id_aluno = a.id_aluno                                             ");
		sql.append("      left join turmas t on t.id_turma = at.id_turma                                                    ");
		sql.append("      left join programas po on t.id_programa = po.id_programa                                          ");
		sql.append("      left join projetos pr on t.id_projeto = pr.id_projeto                                             ");
		sql.append("      left join graus_instrucao gi on gi.id_grau_instrucao = p.graus_inscrucao_pf                       ");
		sql.append("      left join unidades u on u.id_unidade = a.id_unidade                                               ");
		sql.append(" WHERE 1 = 1                                                                                            ");
		sql.append("  and p.id_instituicao = :idInstituicao                                                                 ");
		
		if(StringUtils.isNotEmpty(cpf)) {
			sql.append("  and :p_nr_cpf = p.nr_cpf                                                                          ");
		}
		
		if(Objects.nonNull(dataInicioEntradaInstituicao)) {
			sql.append("   AND DATE_TRUNC('DAY', a.dt_entrada) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_entrada ,'dd/mm/yyyy') )        ");
		}
		if(Objects.nonNull(dataFimEntradaInstituicao)) {
			sql.append("   AND DATE_TRUNC('DAY', a.dt_desligamento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_entrada ,'dd/mm/yyyy') )        ");
		}
		
		if(Objects.nonNull(dataInicioVigenciaInstituicao) && Objects.nonNull(dataFimVigenciaInstituicao)) {
			sql.append("  and fn_entre_periodos ( DATE_TRUNC('DAY', a.dt_entrada) , DATE_TRUNC('DAY', a.dt_desligamento) , :p_dt_inicio_vigencia, :p_dt_fim_vigencia) = 'V'          ");
		}

		
		if(Objects.nonNull(idBeneficiario)) {
			sql.append("  and :p_id_aluno = a.id_aluno                            ");
		}
		
		if(Objects.nonNull(idMae)) {
			sql.append("  and :p_id_pessoa_fisica_mae = pfm.id_pessoa_fisica               ");
		}
		
		if(Objects.nonNull(idPai)) {
			sql.append("  and :p_id_pessoa_fisica_pai = pfp.id_pessoa_fisica               ");
		}
		
		if(Objects.nonNull(idPrograma)) {
			sql.append("  and :p_id_programa = poa.id_programa or :p_id_programa = po.id_programa ");
		}
		
		if(Objects.nonNull(idProjeto)) {
			sql.append("  and :p_id_projeto = pra.id_projeto or :p_id_projeto = pr.id_projeto        ");
		}
		
		if(Objects.nonNull(idUnidade)) {
			sql.append("  and :p_id_unidade = u.id_unidade  ");
		}
		
		
		sql.append(" order by p.nm_pessoa_fisica, p.nm_mae  ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		if(StringUtils.isNotEmpty(cpf)) {
			query.setParameter("p_nr_cpf", NumeroUtil.extrairNumerosMatches(cpf));
		}
		
		if(Objects.nonNull(dataInicioEntradaInstituicao)) {
			query.setParameter("p_dt_inicio_entrada", Java8DateUtil.getLocalDateFormater(dataInicioEntradaInstituicao));
		}
		
		if(Objects.nonNull(dataFimEntradaInstituicao)) {
			query.setParameter("p_dt_fim_entrada", Java8DateUtil.getLocalDateFormater(dataFimEntradaInstituicao));
		}
				
		if(Objects.nonNull(dataInicioVigenciaInstituicao)) {
			query.setParameter("p_dt_inicio_vigencia", Java8DateUtil.getLocalDateFormater(dataInicioVigenciaInstituicao));
		}
		
		if(Objects.nonNull(dataFimVigenciaInstituicao)) {
			query.setParameter("p_dt_fim_vigencia", Java8DateUtil.getLocalDateFormater(dataFimVigenciaInstituicao));
		}
		
		if(Objects.nonNull(idBeneficiario)) {
			query.setParameter("p_id_aluno", idBeneficiario);
		}
		
		if(Objects.nonNull(idMae)) {
			query.setParameter("p_id_pessoa_fisica_mae", idMae);
		}

		if(Objects.nonNull(idPai)) {
			query.setParameter("p_id_pessoa_fisica_pai", idPai);
		}
		
		if(Objects.nonNull(idPrograma)) {
			query.setParameter("p_id_programa", idPrograma);
		}

		if(Objects.nonNull(idProjeto)) {
			query.setParameter("p_id_projeto", idProjeto);
		}

		if(Objects.nonNull(idUnidade)) {
			query.setParameter("p_id_unidade", idUnidade);
		}

		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ExportacaoDadosAlunoDTO> retorno = new ArrayList<ExportacaoDadosAlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ExportacaoDadosAlunoDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
