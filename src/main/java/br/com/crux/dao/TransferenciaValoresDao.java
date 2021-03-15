package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.TransferenciaValoresDTO;
import br.com.crux.infra.util.Java8DateUtil;

@Component
public class TransferenciaValoresDao extends BaseDao{
	
	
	public Optional<List<TransferenciaValoresDTO>> getAllFilter(Long idInstituicao, Long idContaBancariaOrigem, Long idContaBancariaDestino, LocalDate data, Double valor){
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" 	select mov.id_movimentacao,                                                                                                              "); 
		sql.append("       contaorigem.nm_banco || ' - Agência: ' || contaorigem.nr_agencia || ' - Conta: ' || contaorigem.nr_conta_bancaria as conta_origem,     ");
		sql.append("       contadestino.nm_banco || ' - Agência: ' || contadestino.nr_agencia || ' - Conta: ' || contadestino.nr_conta_bancaria as conta_destino, ");
		sql.append("       mov.vl_movimentacao,                                                                                                                   ");
		sql.append("       mov.dt_documento                                                                                                                      ");
		sql.append("   from movimentacoes mov                                                                                                                    ");
		sql.append("   inner join unidades uni on uni.id_unidade = mov.id_unidade                                                                                ");
		sql.append("   inner join instituicoes inst on inst.id_instituicao = uni.id_instituicao                                                                  ");
		sql.append("   inner join contas_bancarias contaorigem on contaorigem.id_conta_bancaria = mov.id_conta_bancaria                                          ");
		sql.append("   inner join contas_bancarias contadestino on contadestino.id_conta_bancaria = mov.id_conta_bancaria_destino                                ");
		sql.append(" where 1=1                                                                                                                                   ");
		sql.append("   and mov.st_tipo_movimentacao         = 'T'                                                                                                ");
		sql.append("   and inst.id_instituicao              = :idInstituicao                                                                                     ");
		
		if(Objects.nonNull(idContaBancariaOrigem)) {
			sql.append("   and mov.id_conta_bancaria = :p_idContaBancariaOrigem                                                                                  ");
		}
		
		if(Objects.nonNull(idContaBancariaDestino)) {
			sql.append("   and mov.id_conta_bancaria_destino    =  :p_idContaBancariaDestino                                                                                                  ");
		}
		
		if(Objects.nonNull(valor)) {
			sql.append("   and mov.vl_movimentacao              =  :p_valor                                                                                                  ");
		}
		
		if(Objects.nonNull(data)) {
			sql.append("   AND DATE_TRUNC('DAY', mov.dt_documento) = DATE_TRUNC('DAY', to_date( :p_data ,'dd/mm/yyyy') )                                            ");
		}
			
		
		sql.append(" order by mov.dt_documento, mov.id_conta_bancaria");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		if(Objects.nonNull(idContaBancariaOrigem)) {
			query.setParameter("p_idContaBancariaOrigem", idContaBancariaOrigem);
		}

		if(Objects.nonNull(idContaBancariaDestino)) {
			query.setParameter("p_idContaBancariaDestino", idContaBancariaDestino);
		}
		
		if(Objects.nonNull(data)) {
			query.setParameter("p_data", Java8DateUtil.getLocalDateFormater(data));
		}
		
		if(Objects.nonNull(valor)) {
			query.setParameter("p_valor", valor);
		}

		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<TransferenciaValoresDTO> retorno = new ArrayList<TransferenciaValoresDTO>();
		values.stream().forEach( colunas -> retorno.add(new TransferenciaValoresDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
