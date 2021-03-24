package br.com.crux.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.SaldoContaBancariaDTO;
import br.com.crux.infra.util.DataUtil;

@Component
public class SaldoContaBancariaDao extends BaseDao{
	
	
	public SaldoContaBancariaDTO getSaldoContaBancaria(LocalDate dataInicio, LocalDate dataFinal, Long idContaBancaria) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select saldo_inicio, saldo_fim                                                                     ");
		sql.append("  from (select fn_retorna_saldo_conta_bancaria(:p_di_conta_bancaria, :p_dt_inicio) saldo_inicio,      ");
		sql.append("               fn_retorna_saldo_conta_bancaria(:p_di_conta_bancaria, :p_dt_fim) saldo_fim             ");
		sql.append("        ) as saldos                                                                                 ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		query.setParameter("p_di_conta_bancaria",  idContaBancaria);
		
      	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
      	Date pDataFinal  = DataUtil.parseDate(dataFinal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
      	
      	
		query.setParameter("p_dt_inicio", new Timestamp(DataUtil.adicionaDia(pDataInicio, -1).getTime()));
		query.setParameter("p_dt_fim", new Timestamp(pDataFinal.getTime()));
		
		Object[] values = (Object[]) query.getSingleResult();
		return new SaldoContaBancariaDTO(values);
	}
	
}
