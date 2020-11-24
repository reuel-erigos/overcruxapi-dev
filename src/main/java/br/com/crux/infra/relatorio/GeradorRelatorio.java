package br.com.crux.infra.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class GeradorRelatorio {

	public byte[] gerarPDF(Map<String, Object> parametros, List<String> dados, String nomeRelatorio, String pathRelatorio) throws JRException, SQLException, IOException {
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		return gerarRelatorioPDF(parametros, dados, nomeRelatorio, pathRelatorio);
	}
	
	public byte[] gerarExcel(Map<String, Object> parametros, List<String> dados, String nomeRelatorio, String pathRelatorio) throws JRException, SQLException, IOException {
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		return gerarRelatorioExcel(parametros, dados, nomeRelatorio, pathRelatorio);
	}


	private byte[] gerarRelatorioExcel(Map<String, Object> parametros, List<String> dados, String nomeRelatorio, String pathRelatorio) throws JRException {
		parametros.put("IS_IGNORE_PAGINATION", true);
		parametros.put("P_PATH_COMPILADO", pathRelatorio);
		
		
		InputStream jasperStream = this.getClass().getResourceAsStream(File.separator + "relatorios" + File.separator + pathRelatorio + File.separator +nomeRelatorio+".jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dados);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanColDataSource);
		
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "dados" });
        reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
        reportConfigXLS.setForcePageBreaks(false);
        reportConfigXLS.setWrapText(false);
        reportConfigXLS.setCollapseRowSpan(true);
        exporter.setConfiguration(reportConfigXLS);
        
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));        
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
        
        exporter.exportReport();
        return xlsReport.toByteArray();
	}


	private byte[] gerarRelatorioPDF(Map<String, Object> parametros, List<String> dados, String nomeRelatorio, String pathRelatorio) throws JRException {
		parametros.put("IS_IGNORE_PAGINATION", false);
		parametros.put("P_PATH_COMPILADO", pathRelatorio);
		
		InputStream jasperStream = this.getClass().getResourceAsStream(File.separator + "relatorios" + File.separator + pathRelatorio + File.separator +nomeRelatorio+".jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dados);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanColDataSource);
		
		byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		return bytes;
	}
		
	
}
