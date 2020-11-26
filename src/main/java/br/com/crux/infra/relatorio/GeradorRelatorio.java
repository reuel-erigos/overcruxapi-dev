package br.com.crux.infra.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Component
public class GeradorRelatorio {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	/**
	 * 
	 * @param parametros
	 * @param dados
	 * @param nomeRelatorio
	 * @param pathRelatorio
	 * @param tipo (pdf, xls)
	 * @return
	 * @throws JRException
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] gerar(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, String[] path, String tipo) throws Exception {
		TipoRelatorio tipoRelatorio = TipoRelatorio.getPorTipo(tipo);	
		
		Exporter exporter = null;		
		switch (tipoRelatorio) {
		case PDF:
			exporter = new JRPdfExporter();
			break;
		case XLS:
			exporter = new JRXlsExporter();
			break;
		default:
			throw new Exception("Não é possível gerar o tipo de relatório informado (" + tipoRelatorio + "). Suportado apenas:[1-PDF, 2-XLS]");
		}
		
		String pathCompleto = String.join(File.separator, path);
		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		parametros.put("P_PATH_ROOT", "relatorios"+File.separator+path[0]);
		
		final Resource fileResource = resourceLoader.getResource("classpath:relatorios" + File.separator + pathCompleto + File.separator +nomeRelatorio+".jasper");
		InputStream jasperStream = fileResource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dados);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanColDataSource);
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		
		ByteArrayOutputStream baosReport = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baosReport));
		exporter.exportReport();
		
		byte[] toReturn = baosReport.toByteArray();
		baosReport.close();
				
		return toReturn;
	}
	
	
}
