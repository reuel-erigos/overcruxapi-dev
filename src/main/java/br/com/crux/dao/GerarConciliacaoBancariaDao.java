package br.com.crux.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.infra.util.DataUtil;

@Component
public class GerarConciliacaoBancariaDao extends BaseDao {
	
	public void gerar(Long idInstituicao, Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim) {
        Session session =null;
        try{
        	session = getSession();
            session.beginTransaction();
            
            session.doWork(new Work(){
              @Override
              public void execute(Connection connection) throws SQLException {
            	  Date pDataInicio = null;
            	  Date pDataFim    = null; 
            	  try {
            		  CallableStatement statement =null;
            		  
            		  String sqlString = "{call fn_gerar_conciliacao_bancaria(?,?,?,?)}";
            		  
            		  statement = connection.prepareCall(sqlString);
            		  statement.setLong(1,idInstituicao);
            		  
            		  if(Objects.nonNull(idContaBancaria)) {
            			  statement.setLong(2,idContaBancaria);
            		  } else {
            			  statement.setNull(2, Types.INTEGER);
            		  }
            		  
            		  pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            		  statement.setTimestamp(3, new Timestamp(pDataInicio.getTime()));
            		  
            		  pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            		  statement.setTimestamp(4, new Timestamp(pDataFim.getTime()));
            		  
            		  int retorno = statement.executeUpdate();
            		  if(retorno != 0) {
            			  throw new ConciliacaoNaoGeradoException("Erro ao gerar a conciliação bancária, código erro banco: " + retorno);
            		  }
            	  } catch (Exception e) {
        			  throw new ConciliacaoNaoGeradoException("Erro ao gerar a conciliação bancária. Parametros idInstituicao: " + idInstituicao + " - idContaBancaria: "+ idContaBancaria + " - Data Inicio: " + pDataInicio + " - Data Fim: " + pDataFim + " - Erro: "+ e.getMessage());
            		  
  				  } finally { }
                }
            });
            session.getTransaction().commit();

		}
        finally{
        	
        	closeEntityManager();
        }
	}

	
}
