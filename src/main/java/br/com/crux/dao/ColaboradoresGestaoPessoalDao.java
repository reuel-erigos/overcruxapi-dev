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
import br.com.crux.dao.dto.ColaboradoresGestaoPessoalDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.infra.util.NumeroUtil;

@Component
public class ColaboradoresGestaoPessoalDao extends BaseDao{
	
	
	public Optional<List<ColaboradoresGestaoPessoalDTO>> getAllFilter(String cpf, Long idColaborador,  Long idUnidade, Long idDepartamento, Long idCargo, 
			                                                          Long idFuncao, LocalDate dataInicio, LocalDate dataFim, Long idInstituicao){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select p.id_pessoa_fisica                                                                                                          ");
		sql.append("        ,colaborador.id_funcionario                                                                                                 ");
		sql.append("        ,p.nm_pessoa_fisica                                                                                                         ");
		sql.append("        ,p.ds_email                                                                                                                 ");
		sql.append("        ,u.cd_unidade || ' - ' || u.nm_unidade as unidade                                                                           ");
		sql.append("        ,du.cd_unidade_departamento || ' - ' || du.nm_departamento as departamento                                                  ");
		sql.append("        ,c.cd_cargo || ' - ' || c.nm_cargo  as cargo                                                                                ");
		sql.append("        ,f.nm_funcao                                                                                                                ");
		sql.append("    from pessoas_fisicas p                                                                                                          ");
		sql.append("      inner join funcionarios colaborador on p.id_pessoa_fisica = colaborador.id_pessoa_fisica                                      ");
		sql.append("      left join unidades u on u.id_unidade = colaborador.id_unidade                                                                 ");
		sql.append("      left join departamentos_unidades du on du.id_unidade = u.id_unidade and colaborador.id_departamento = du.id_departamento      ");
		sql.append("      left join funcoes_instituicao fi on fi.id_funcionario = colaborador.id_funcionario                                            ");
		sql.append("      left join funcoes f on f.id_funcao = fi.id_funcao                                                                             ");
		sql.append("      left join cargos c on c.id_cargo = colaborador.id_cargo                                                                       ");
		sql.append(" WHERE 1 = 1                                                                                                                        ");
		sql.append("  and p.id_instituicao = :idInstituicao                                                                                             ");
		
		if(StringUtils.isNotEmpty(cpf)) {
			sql.append("  and :p_nr_cpf = p.nr_cpf  ");
		}
		
		if(Objects.nonNull(idColaborador)) {
			sql.append("  and :p_id_colaborador = colaborador.id_funcionario  ");
		}
		
		if(Objects.nonNull(idUnidade)) {
			sql.append("  and :p_id_unidade = u.id_unidade  ");
		}

		if(Objects.nonNull(idDepartamento)) {
			sql.append("  and :p_id_departamento = du.id_departamento   ");
		}

		if(Objects.nonNull(idCargo)) {
			sql.append("  and :p_id_cargo = c.id_cargo   ");
		}

		if(Objects.nonNull(idFuncao)) {
			sql.append("  and :p_id_funcao = f.id_funcao   ");
		}

		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_nascimento) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'dd/mm/yyyy') )        ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_nascimento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim ,'dd/mm/yyyy') )        ");
		}

		
		sql.append(" order by p.nm_pessoa_fisica, p.nm_pessoa_fisica  ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		if(StringUtils.isNotEmpty(cpf)) {
			query.setParameter("p_nr_cpf", NumeroUtil.extrairNumerosMatches(cpf));
		}
		
		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}

		if(Objects.nonNull(idUnidade)) {
			query.setParameter("p_id_unidade", idUnidade);
		}

		if(Objects.nonNull(idColaborador)) {
			query.setParameter("p_id_colaborador", idColaborador);
		}

		if(Objects.nonNull(idDepartamento)) {
			query.setParameter("p_id_departamento", idDepartamento);
		}

		if(Objects.nonNull(idCargo)) {
			query.setParameter("p_id_cargo", idCargo);
		}

		if(Objects.nonNull(idFuncao)) {
			query.setParameter("p_id_funcao", idFuncao);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ColaboradoresGestaoPessoalDTO> retorno = new ArrayList<ColaboradoresGestaoPessoalDTO>();
		values.stream().forEach( colunas -> retorno.add(new ColaboradoresGestaoPessoalDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
