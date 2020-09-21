package br.com.crux.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ConciliacaoDTO;
import br.com.crux.infra.util.DataUtil;

@Component
public class ConciliacaoDao extends BaseDao {
	
	public void gerar(Long idInstituicao, Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim) {
        Session session =null;
        try{
        	session = getSession();
            session.beginTransaction();
            
            session.doWork(new Work(){
              @Override
              public void execute(Connection connection) throws SQLException {
                    CallableStatement statement =null;
                    
                    String sqlString = "{call fn_gerar_conciliacao_bancaria(?,?,?,?)}";

                    statement = connection.prepareCall(sqlString);
                    statement.setLong(1,idInstituicao);
                    
                    if(Objects.nonNull(idContaBancaria)) {
                    	statement.setLong(2,idContaBancaria);
                    } else {
                    	statement.setNull(2, Types.INTEGER);
                    }
                    
                	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                	statement.setTimestamp(3, new Timestamp(pDataInicio.getTime()));
                
                	Date pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                	statement.setTimestamp(4, new Timestamp(pDataFim.getTime()));
                    
                    int count = statement.executeUpdate();
                    System.out.println(count +" registro(s) afetados");
                }
            });
            session.getTransaction().commit();

        }finally{
        	closeEntityManager();
        }
	}

	@SuppressWarnings({ "unchecked"})
	public List<ConciliacaoDTO> getAll(Long idInstituicao, Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim) {
		Session session =null;
		try {
			session = getSession();
	        session.beginTransaction();
			
			ProcedureCall procedureCall =  session.createStoredProcedureCall("fn_get_conciliacao_bancaria");
			procedureCall.registerParameter(1, void.class, ParameterMode.REF_CURSOR);

			procedureCall.registerParameter(2, BigDecimal.class, ParameterMode.IN);
			procedureCall.registerParameter(3, BigDecimal.class, ParameterMode.IN);
			procedureCall.registerParameter(4, Timestamp.class, ParameterMode.IN);
			procedureCall.registerParameter(5, Timestamp.class, ParameterMode.IN);
			
			procedureCall.getParameterRegistration(2).bindValue(new BigDecimal(idInstituicao));
			
			if(Objects.nonNull(idContaBancaria)) {
				procedureCall.getParameterRegistration(3).bindValue(new BigDecimal(idContaBancaria)); 
			}else {
				procedureCall.getParameterRegistration(3).enablePassingNulls(true);
			}
			
	    	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			procedureCall.getParameterRegistration(4).bindValue(new java.sql.Date(pDataInicio.getTime()));
			
	    	Date pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			procedureCall.getParameterRegistration(5).bindValue(new java.sql.Date(pDataFim.getTime())); 

			ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
			ResultSetOutput resultSetOutput = (ResultSetOutput)procedureOutputs.getCurrent();
			
			List<Object[]> values = resultSetOutput.getResultList();

			List<ConciliacaoDTO> retorno = new ArrayList<ConciliacaoDTO>();
			values.stream().forEach(colunas -> retorno.add(new ConciliacaoDTO(colunas)));

			session.getTransaction().commit();
			return retorno;
	
        }finally{
        	closeEntityManager();
        }
		
	}
	
}
