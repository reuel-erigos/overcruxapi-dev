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
import br.com.crux.dao.dto.ExportarDadosBeneficiarioDTO;
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
			sql.append("  and fn_entre_periodos ( a.dt_entrada , a.dt_desligamento , to_date(:p_dt_inicio_vigencia,'dd/mm/yyyy') , to_date(:p_dt_fim_vigencia,'dd/mm/yyyy')  ) = 'V'          ");
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
	
	

	
	
	public List<ExportarDadosBeneficiarioDTO> getDadosExportarBeneficiario(Long idBeneficiario){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nome_aluno                                           ,");
		sql.append("	    dt_nascimento_aluno                                 ,");
		sql.append("	    naturalidade_aluno                                  ,");
		sql.append("	    uf_nascimento_aluno                                 ,");
		sql.append("	    sexo_aluno                                          ,");
		sql.append("	    raca_aluno                                          ,");
		sql.append("	    nm_mae_aluno                                        ,");
		sql.append("	    nm_pai_aluno                                        ,");
		sql.append("	    estado_civil_aluno                                  ,");
		sql.append("	    tipo_sangue_aluno                                   ,");
		sql.append("	    nr_cep_aluno                                        ,");
		sql.append("	    ds_endereco_aluno                                   ,");
		sql.append("	    cidade_aluno                                        ,");
		sql.append("	    ds_bairro_aluno                                     ,");
		sql.append("	    uf_endereco_aluno                                   ,");
		sql.append("	    ponto_referencia_aluno                              ,");
		sql.append("	    fone_residencial_aluno                              ,");
		sql.append("	    fone_celular_aluno                                  ,");
		sql.append("	    email_aluno                                         ,");
		sql.append("	    st_autoriza_email_aluno                             ,");
		sql.append("	    fone_recado_aluno                                   ,");
		sql.append("	    nr_matricula_aluno                                  ,");
		sql.append("	    unidade_aluno                                       ,");
		sql.append("	    programa_aluno                                      ,");
		sql.append("	    projeto_aluno                                       ,");
		sql.append("	    dt_entrada_aluno                                    ,");
		sql.append("	    dt_desligamento_aluno                               ,");
		sql.append("	    st_mora_pais_aluno                                  ,");
		sql.append("	    st_pais_casados_aluno                               ,");
		sql.append("	    publico_prioritario_aluno                           ,");
		sql.append("	    busca_escola_aluno                                  ,");
		sql.append("	    ds_desligamento_aluno                               ,");
		sql.append("	    observacoes_aluno                                   ,");
		sql.append("	    descricao_aluno                                     ,");
		sql.append("	    matriculado_esc_pub_aluno                           ,");
		sql.append("	    nivel_escolaridade_aluno                            ,");
		sql.append("	    grau_inscrucao_aluno                                ,");
		sql.append("	    tipo_escola_aluno                                   ,");
		sql.append("	    ds_turno_aluno                                      ,");
		sql.append("	    escola_aluno                                        ,");
		sql.append("	    serie_escola_aluno                                  ,");
		sql.append("	    ra_escola_aluno                                     ,");
		sql.append("	    nr_cpf_aluno                                        ,");
		sql.append("	    nis_aluno                                           ,");
		sql.append("	    pis_pasep_aluno                                     ,");
		sql.append("	    identidade_aluno                                    ,");
		sql.append("	    orgao_expedidor_ci_aluno                            ,");
		sql.append("	    uf_ci_aluno                                         ,");
		sql.append("	    dt_emissao_ci_aluno                                 ,");
		sql.append("	    titulo_eleitor_aluno                                ,");
		sql.append("	    zona_titulo_aluno                                   ,");
		sql.append("	    sessao_titulo_aluno                                 ,");
		sql.append("	    certificado_reservista_aluno                        ,");
		sql.append("	    regiao_militar_reservista_aluno                     ,");
		sql.append("	    uf_reservista_aluno                                 ,");
		sql.append("	    nr_cnh_aluno                                        ,");
		sql.append("	    categoria_cnh_aluno                                 ,");
		sql.append("	    dt_vencimento_cnh_aluno                             ,");
		sql.append("	    carteira_trabalho_aluno                             ,");
		sql.append("	    serie_carteira_trabalho_aluno                       ,");
		sql.append("	    uf_carteira_trabalho_aluno                          ,");
		sql.append("	    possui_deficiente_aluno                             ,");
		sql.append("	    ds_deficiencia_aluno                                ,");
		sql.append("	    ds_problema_saude_aluno                             ,");
		sql.append("	    ds_medicamentos_controlados_aluno                   ,");
		sql.append("	    condicao_moradia_aluno                              ,");
		sql.append("	    ds_condicao_moradia_aluno                           ,");
		sql.append("	    st_atendido_orgao_rede_aluno                        ,");
		sql.append("	    ds_atendido_orgao_rede_aluno                        ,");
		sql.append("	    beneficios_aluno                                    ,");
		sql.append("	    ds_rede_ap_soc_relev_aluno                          ,");
		sql.append("	    ds_rede_apoio_social_aluno                          ,");
		sql.append("	    vl_beneficios_aluno                                 ,");
		sql.append("	    origem_renda_aluno                                  ,");
		sql.append("	    entidade_social                                     ,");
		sql.append("	    data_encaminhamento_aluno                           ,");
		sql.append("	    ds_encaminhamento_aluno                             ,");
		sql.append("	    nome_familiar                                       ,");
		sql.append("	    dt_nascimento_familiar                              ,");
		sql.append("	    naturalidade_familiar                               ,");
		sql.append("	    uf_nascimento_familiar                              ,");
		sql.append("	    sexo_familiar                                       ,");
		sql.append("	    raca_familiar                                       ,");
		sql.append("	    nm_mae_familiar                                     ,");
		sql.append("	    nm_pai_familiar                                     ,");
		sql.append("	    estado_civil_familiar                               ,");
		sql.append("	    tipo_sangue_familiar                                ,");
		sql.append("	    nr_cep_familiar                                     ,");
		sql.append("	    ds_endereco_familiar                                ,");
		sql.append("	    cidade_familiar                                     ,");
		sql.append("	    ds_bairro_familiar                                  ,");
		sql.append("	    uf_endereco_familiar                                ,");
		sql.append("	    ponto_referencia_familiar                           ,");
		sql.append("	    fone_residencial_familiar                           ,");
		sql.append("	    fone_celular_familiar                               ,");
		sql.append("	    email_familiar                                      ,");
		sql.append("	    st_autoriza_email_familiar                          ,");
		sql.append("	    dt_cadastro_familiar                                ,");
		sql.append("	    dt_desligamento_familiar                            ,");
		sql.append("	    st_situacao_parentesco_familiar                     ,");
		sql.append("	    ds_grau_parentesco_familiar                         ,");
		sql.append("	    ds_desligamento_familiar                            ,");
		sql.append("	    ds_outras_informacoes_familiar                      ,");
		sql.append("	    dt_vinculacao_familiar                              ,");
		sql.append("	    dt_desvinculacao_familiar                           ,");
		sql.append("	    st_mesmo_ender_resp_familiar                        ,");
		sql.append("	    st_transporta_aluno_familiar                        ,");
		sql.append("	    st_tutela_aluno_familiar                            ,");
		sql.append("	    st_resp_fin_aluno_familiar                          ,");
		sql.append("	    ds_grau_parentesco_responsavel_familiar             ,");
		sql.append("	    ds_desligamento_responsavl_familiar                 ,");
		sql.append("	    descricao_familiar                                  ,");
		sql.append("	    nivel_escolaridade_familiar                         ,");
		sql.append("	    grau_inscrucao_familiar                             ,");
		sql.append("	    nr_cpf_familiar                                     ,");
		sql.append("	    nis_familiar                                        ,");
		sql.append("	    pis_pasep_familiar                                  ,");
		sql.append("	    identidade_familiar                                 ,");
		sql.append("	    orgao_expedidor_ci_familiar                         ,");
		sql.append("	    uf_ci_familiar                                      ,");
		sql.append("	    dt_emissao_ci_familiar                              ,");
		sql.append("	    titulo_eleitor_familiar                             ,");
		sql.append("	    zona_titulo_familiar                                ,");
		sql.append("	    sessao_titulo_familiar                              ,");
		sql.append("	    certificado_reservista_familiar                     ,");
		sql.append("	    regiao_militar_reservista_familiar                  ,");
		sql.append("	    uf_reservista_familiar                              ,");
		sql.append("	    nr_cnh_familiar                                     ,");
		sql.append("	    categoria_cnh_familiar                              ,");
		sql.append("	    dt_vencimento_cnh_familiar                          ,");
		sql.append("	    carteira_trabalho_familiar                          ,");
		sql.append("	    vserie_carteira_trabalho_familiar                   ,");
		sql.append("	    uf_carteira_trabalho_familiar                       ,");
		sql.append("	    ds_profissao_familiar                               ,");
		sql.append("	    nm_empresa_trabalho_familiar                        ,");
		sql.append("	    st_ben_bolsa_familia_familiar                       ,");
		sql.append("	    vl_bolsa_familia_familiar                           ,");
		sql.append("	    vl_renda_familiar                                   ,");
		sql.append("	    fone_comercial_familiar                             ,");
		sql.append("	    ds_situacao_trabalho_familiar                       ,");
		sql.append("	    ds_motivo_nao_trab_familiar                         ,");
		sql.append("	    tipo_escola_familiar                                ,");
		sql.append("	    ds_turno_familiar                                   ,");
		sql.append("	    escola_familiar                                     ,");
		sql.append("	    ds_curso_escola_familiar                            ,");
		sql.append("	    ds_periodo_escola_familiar                          ,");
		sql.append("	    serie_escola_familiar                               ,");
		sql.append("	    ra_escola_familiar                                  ,");
		sql.append("	    possui_deficiente_familiar                          ,");
		sql.append("	    ds_deficiencia_familiar                             ,");
		sql.append("	    ds_problema_saude_familiar                          ,");
		sql.append("	    ds_medicamentos_controlados_familiar                ,");
		sql.append("	    condicao_moradia_familiar                           ,");
		sql.append("	    ds_condicao_moradia_familiar                        ,");
		sql.append("	    ds_atendido_orgao_rede_familiar                     ,");
		sql.append("	    beneficios_familiar                                 ,");
		sql.append("	    ds_rede_ap_soc_relev_familiar                       ,");
		sql.append("	    ds_rede_apoio_social_familiar                       ,");
		sql.append("	    vl_beneficios_familiar                              ,");
		sql.append("	    origem_renda_familiar                               ,");
		sql.append("	    id_pessoa_fisica                                    ,");
		sql.append("	    id_aluno                                            ,");
		sql.append("	    st_apr_externa_aluno                                 ");
		sql.append("	from vw_exporta_dados_beneficiarios v                    ");
		sql.append(" where v.id_aluno = " + idBeneficiario                        );
		
		//sql.append(" where v.id_aluno in (");
		//sql.append(String.join(", ", listaIdsBeneficiario.stream().map(String::valueOf).collect(Collectors.toList())));
		//sql.append(" )");
		
		sql.append(" order by nome_aluno                                         ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ExportarDadosBeneficiarioDTO> retorno = new ArrayList<ExportarDadosBeneficiarioDTO>();
		values.stream().forEach( colunas -> retorno.add(new ExportarDadosBeneficiarioDTO(colunas)));
		
		return retorno;		
	}
	
}
