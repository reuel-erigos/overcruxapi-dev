package br.com.crux.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.infra.util.DataUtil;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoDTO;

@Component
public class SaldoProjetoDao extends BaseDao{
	
	

	@SuppressWarnings({ "unchecked"})
	public List<SaldoProjetoDTO> getAllFilter(Long idInstituicao, Long idPrograma, Long idProjeto, LocalDate dataInicio, LocalDate dataFim){
		Session session =null;
		try {
			session = getSession();
	        session.beginTransaction();
			
			ProcedureCall procedureCall =  session.createStoredProcedureCall("fn_get_extrato_projeto");
			procedureCall.registerParameter(1, void.class, ParameterMode.REF_CURSOR);

			procedureCall.registerParameter(2, BigDecimal.class, ParameterMode.IN);
			procedureCall.registerParameter(3, BigDecimal.class, ParameterMode.IN);
			procedureCall.registerParameter(4, BigDecimal.class, ParameterMode.IN);
			procedureCall.registerParameter(5, Timestamp.class, ParameterMode.IN);
			procedureCall.registerParameter(6, Timestamp.class, ParameterMode.IN);
			
			
			procedureCall.getParameterRegistration(2).bindValue(new BigDecimal(idInstituicao));
			
			procedureCall.getParameterRegistration(3).enablePassingNulls(true);
			procedureCall.getParameterRegistration(3).bindValue(new BigDecimal(idPrograma));
			
			procedureCall.getParameterRegistration(4).enablePassingNulls(true);
			procedureCall.getParameterRegistration(4).bindValue(new BigDecimal(idProjeto));
			
			
	    	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			procedureCall.getParameterRegistration(5).bindValue(new java.sql.Date(pDataInicio.getTime()));
			
	    	Date pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			procedureCall.getParameterRegistration(6).bindValue(new java.sql.Date(pDataFim.getTime())); 
			
			ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
			ResultSetOutput resultSetOutput = (ResultSetOutput)procedureOutputs.getCurrent();
			
			List<Object[]> values = resultSetOutput.getResultList();

			List<SaldoProjetoDTO> retorno = new ArrayList<SaldoProjetoDTO>();
			values.stream().forEach(colunas -> retorno.add(new SaldoProjetoDTO(colunas)));

			session.getTransaction().commit();
			return retorno;
	
        }catch (Exception e) {
  		  throw new ConciliacaoNaoGeradoException("Não foi possível obter os dados para o relatório de saldo por projetos.");
		}finally{
        	closeEntityManager();
        }
		
	}
	

	
}
