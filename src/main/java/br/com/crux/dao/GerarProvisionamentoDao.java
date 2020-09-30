package br.com.crux.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.util.DataUtil;

@Component
public class GerarProvisionamentoDao extends BaseDao {

	public void gerar(Long idInstituicao, LocalDate dataInicio, LocalDate dataFim) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					try {
						CallableStatement statement = null;

						String sqlString = "{call fn_gerar_provisoes_financeiras(?,?,?)}";

						statement = connection.prepareCall(sqlString);
						statement.setLong(1, idInstituicao);

						Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						statement.setTimestamp(2, new Timestamp(pDataInicio.getTime()));

						Date pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						statement.setTimestamp(3, new Timestamp(pDataFim.getTime()));

						int retorno = statement.executeUpdate();
						if (retorno != 0) {
							throw new ConciliacaoNaoGeradoException("Erro ao gerar a provisionamento, código erro banco: " + retorno);
						}

					} catch (NegocioException e) {
						throw new ProvisionamentoNaoGeradoException(e.getMessage());

					} catch (Exception e) {
						String msg = "Where:";
						if (e.getMessage().contains(msg)) {
							throw new ProvisionamentoNaoGeradoException(e.getMessage().substring(0, e.getMessage().indexOf(msg)));
						}
						throw new ProvisionamentoNaoGeradoException(e.getMessage());
					}

				}
			});
			session.getTransaction().commit();

		} finally {
			closeEntityManager();
		}
	}

}
