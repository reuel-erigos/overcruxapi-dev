package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;


@Component
public class MovimentacaoContabilExcelFileExporter {
	
	public byte[] gerar(Object[][] dados, String[] nomesCelulasHeader) {
		ByteArrayInputStream stream = gerarFileExcel(dados, nomesCelulasHeader);
        byte[] targetArray = new byte[stream.available()];
        try {
			stream.read(targetArray);
		} catch (IOException e) {
			throw new NegocioException	(e.getMessage());
		}
        return targetArray;
	}
	
	private ByteArrayInputStream gerarFileExcel(Object[][] dados, String[] nomesCelulasHeader) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Relatório");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Criando as celulas que representam o header
	        for(int h = 0; h < nomesCelulasHeader.length; h++) {
		        Cell cell = row.createCell(h);
		        cell.setCellValue(nomesCelulasHeader[h]);
		        cell.setCellStyle(headerCellStyle);
	        }
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < dados.length ; i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	
	        	// Percorre pelas colunas de cada linha
	        	for (int j = 0; j < dados[i].length; j++) {
	        		Object valor = dados[i][j];
	        		if(valor instanceof Double) {
	        			dataRow.createCell(j).setCellValue(((Number) valor).doubleValue());
	        		} else {
	        			dataRow.createCell(j).setCellValue(((String) valor).toString());
	        		}
	            }
	        }
	
	        // Making size of column auto resize to fit with data
	        for(int h = 0; h < nomesCelulasHeader.length; h++) {
	        	sheet.autoSizeColumn(h);
	        }
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacaoNaoGeradoException("Erro ao gerar o arquivo. " + ex.getMessage());
		}
	}
}
