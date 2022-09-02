package br.com.crux.infra.relatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetArquivoInstituicaoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.enums.TipoArquivoMetadado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class GeradorRelatorio {
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired
	private GetArquivoInstituicaoCmd getArquivoInstituicaoCmd;
	
	
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
	@SuppressWarnings({ "rawtypes" })
	public byte[] gerar(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, String[] path, String mimeType) throws Exception {
		byte[] cabecalho = getArquivoInstituicaoCmd.getInstituicaoTipo(getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId(), TipoArquivoMetadado.CABECALHO_RELATORIO.getCodigo());
		byte[] rodape = getArquivoInstituicaoCmd.getInstituicaoTipo(getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId(), TipoArquivoMetadado.RODAPE_RELATORIO.getCodigo());
		
		
		TipoRelatorio tipoRelatorio = TipoRelatorio.getPorTipo(mimeType);	
		
		String pathCompleto = String.join(File.separator, path);
		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		parametros.put("P_PATH_ROOT", "relatorios"+File.separator+path[0]);
		parametros.put("P_NOME_USUARIO_LOGADO", getUsuarioLogadoCmd.getUsuarioLogado().getNomeUsuario());
		parametros.put("REPORT_LOCALE", new Locale("pt","BR"));
		if(cabecalho != null) { 
			parametros.put("P_CABECALHO", new ByteArrayInputStream(cabecalho));
		} else {
			parametros.put("P_CABECALHO", null);
		}
		if(rodape != null) { 
			parametros.put("P_RODAPE", new ByteArrayInputStream(rodape));
		} else {
			parametros.put("P_RODAPE", null);
		}
		parametros.put("REPORT_TIME_ZONE", TimeZone.getTimeZone("America/Sao_Paulo"));
		
		byte[] toReturn = null;
		Exporter exporter = null;		
		switch (tipoRelatorio) {
		case PDF:
			exporter = new JRPdfExporter();
			toReturn = gerarRelatorioPDF(parametros, dados, nomeRelatorio, exporter, pathCompleto);
			break;
		case XLS:
			exporter = new JRXlsxExporter();
			toReturn = gerarRelatorioExcel(parametros, dados, nomeRelatorio, pathCompleto);
			break;
		default:
			throw new Exception("Não é possível gerar o tipo de relatório informado (" + tipoRelatorio + "). Suportado apenas:[1-PDF, 2-XLS]");
		}
				
		return toReturn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private byte[] gerarRelatorioPDF(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, Exporter exporter, String pathCompleto) throws IOException, JRException {
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
	
	
	private byte[] gerarRelatorioExcel(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, String pathCompleto) throws IOException, JRException {
		parametros.put("IS_IGNORE_PAGINATION", true);		
		
		final Resource fileResource = resourceLoader.getResource("classpath:relatorios" + File.separator + pathCompleto + File.separator +nomeRelatorio+".jasper");
		InputStream jasperStream = fileResource.getInputStream();
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
        reportConfigXLS.setIgnoreGraphics(true);
        exporter.setConfiguration(reportConfigXLS);
        
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));        
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
       
        exporter.exportReport();
        return xlsReport.toByteArray();
	}
	
}
