package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;

@Component
public class FaturasPagarDao extends BaseDao{
	
	
	public Optional<List<FaturasPagarDTO>> getAllFilter(Long idCategoria, Long idEmpresa, Long idPessoaFisica, Long idPrograma, Long idProjeto, 
			                                                          LocalDate dataInicio, LocalDate dataFim,
			                                                          LocalDate dataInicioVenc, LocalDate dataFimVenc){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select p.nm_programa_projeto,                             ");
		sql.append("        p.ds_fornecedor,                                   ");
		sql.append("        p.cnpj_cpf,                                        ");
		sql.append("        p.nr_documento,                                    ");
		sql.append("        p.dt_documento,                                    ");
		sql.append("        p.vl_movimentacao,                                 ");
		sql.append("        p.dt_vencimento,                                   ");
		sql.append("        p.vl_fatura,                                       ");
		sql.append("        p.nr_parcela,                                      ");
		sql.append("        p.ds_fatura,                                       ");
		sql.append("        p.nm_categoria,                                    ");
		sql.append("        p.id_empresa,                                      ");
		sql.append("        p.id_pessoa_fisica,                                ");
		sql.append("        p.id_programa,                                     ");
		sql.append("        p.id_projeto,                                      ");
		sql.append("        p.id_movimentacao                                  ");
		sql.append("   from vw_relatorio_faturas_pagar_periodo p               ");
		sql.append(" WHERE 1 = 1                                               ");
		

		if(Objects.nonNull(idEmpresa)) {
			sql.append("  and :p_empresa = p.id_empresa  ");
		}

		if(Objects.nonNull(idPessoaFisica)) {
			sql.append("  and :p_pessoa_fisica = p.id_pessoa_fisica  ");
		}

		if(Objects.nonNull(idPrograma)) {
			sql.append("  and :p_programa = p.id_programa  ");
		}

		if(Objects.nonNull(idProjeto)) {
			sql.append("  and :p_projeto = p.id_projeto  ");
		}

		if(Objects.nonNull(idCategoria)) {
			sql.append(" and (p.id_movimentacao in (select c.id_movimentacao   ");
			sql.append("	from vw_rateio_itens_movimentacoes_categorias c    ");
			sql.append("	where c.id_categoria = :p_id_categoria)            ");
			sql.append("	or :p_id_categoria is null)                        ");
		}  

		if(Objects.nonNull(dataInicio)) {
			sql.append("   AND to_date( p.dt_documento, 'dd/mm/yyyy') >= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'yyyy-mm-dd') )  ");
		}
		
		if(Objects.nonNull(dataFim)) {
			sql.append("  and to_date( p.dt_documento, 'dd/mm/yyyy') <= DATE_TRUNC('DAY', to_date( :p_dt_inicio ,'yyyy-mm-dd') )     ");
		}
		
		if(Objects.nonNull(dataInicioVenc)) {
			sql.append("   AND to_date( p.dt_vencimento, 'dd/mm/yyyy') >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_venc ,'yyyy-mm-dd') )     ");
		}
		
		if(Objects.nonNull(dataFimVenc)) {
			sql.append("   AND DATE_TRUNC('DAY', p.dt_vencimento) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_venc ,'dd/mm/yyyy') )     ");
		}
		
		sql.append(" order by p.nm_programa_projeto ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(Objects.nonNull(idCategoria)) {
			query.setParameter("p_id_categoria", idCategoria);
		}
		
		if(Objects.nonNull(idEmpresa)) {
			query.setParameter("p_empresa", idEmpresa);
		}

		if(Objects.nonNull(idPessoaFisica)) {
			query.setParameter("p_pessoa_fisica", idPessoaFisica);
		}

		if(Objects.nonNull(idPrograma)) {
			query.setParameter("p_programa", idPrograma);
		}

		if(Objects.nonNull(idProjeto)) {
			query.setParameter("p_programa", idProjeto);
		}
		
		if(Objects.nonNull(dataInicio)) {
			query.setParameter("p_dt_inicio", Java8DateUtil.getLocalDateFormater(dataInicio));
		}
		
		if(Objects.nonNull(dataFim)) {
			query.setParameter("p_dt_fim", Java8DateUtil.getLocalDateFormater(dataFim));
		}

		if(Objects.nonNull(dataInicioVenc)) {
			query.setParameter("p_dt_inicio_venc", Java8DateUtil.getLocalDateFormater(dataInicioVenc));
		}
		
		if(Objects.nonNull(dataFimVenc)) {
			query.setParameter("p_dt_fim_venc", Java8DateUtil.getLocalDateFormater(dataFimVenc));
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<FaturasPagarDTO> retorno = new ArrayList<FaturasPagarDTO>();
		values.stream().forEach( colunas -> retorno.add(new FaturasPagarDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
