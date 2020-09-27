package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ConciliacaoTO;


@Component
public class ConciliacaoBancariaExcelFileExporter {
	
	public ByteArrayInputStream gerarFileExcel(List<ConciliacaoTO> conciliacoes) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Conciliação");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Tipo");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Situação");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Documento");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Data");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(4);
	        cell.setCellValue("Banco");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(5);
	        cell.setCellValue("Categoria");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Fornecedor");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("Complemento");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(8);
	        cell.setCellValue("Centro Custo");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(9);
	        cell.setCellValue("Grupo Contas");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(10);
	        cell.setCellValue("Valor");
	        cell.setCellStyle(headerCellStyle);

	        
	        // Creating data rows for each customer
	        for(int i = 0; i < conciliacoes.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(conciliacoes.get(i).getTipo());
	        	dataRow.createCell(1).setCellValue(conciliacoes.get(i).getSituacao());
	        	dataRow.createCell(2).setCellValue(conciliacoes.get(i).getNumeroDocumento());
	        	dataRow.createCell(3).setCellValue(Java8DateUtil.getLocalDateFormater(conciliacoes.get(i).getData()));
	        	dataRow.createCell(4).setCellValue(conciliacoes.get(i).getBanco());
	        	dataRow.createCell(5).setCellValue(conciliacoes.get(i).getCategoria());
	        	dataRow.createCell(6).setCellValue(conciliacoes.get(i).getFornecedor());
	        	dataRow.createCell(7).setCellValue(conciliacoes.get(i).getComplemento());
	        	dataRow.createCell(8).setCellValue(conciliacoes.get(i).getCentroCusto());
	        	dataRow.createCell(9).setCellValue(conciliacoes.get(i).getGrupoContas());
	        	dataRow.createCell(10).setCellValue(conciliacoes.get(i).getValor());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        sheet.autoSizeColumn(7);
	        sheet.autoSizeColumn(8);
	        sheet.autoSizeColumn(9);
	        sheet.autoSizeColumn(10);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
	        
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
