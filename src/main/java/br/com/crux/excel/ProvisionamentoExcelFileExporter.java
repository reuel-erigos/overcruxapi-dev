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

import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ProvisionamentoTO;


@Component
public class ProvisionamentoExcelFileExporter {
	
	
	public byte[] gerar(List<ProvisionamentoTO> provisoes) {
		ByteArrayInputStream stream = gerarFileExcel(provisoes);
        byte[] targetArray = new byte[stream.available()];
        try {
			stream.read(targetArray);
		} catch (IOException e) {
			throw new NegocioException	(e.getMessage());
		}
        return targetArray;
	}
	
	private ByteArrayInputStream gerarFileExcel(List<ProvisionamentoTO> provisoes) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Provisão");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Código");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(1);
	        cell.setCellValue("Situação");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(2);
	        cell.setCellValue("Data");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Documento");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(4);
	        cell.setCellValue("Complemento");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(5);
	        cell.setCellValue("Conta Destino (Débito)");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Conta Origem (Crédito)");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("Valor");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(8);
	        cell.setCellValue("Centro Custo");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(9);
	        cell.setCellValue("Grupo Contas");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(10);
	        cell.setCellValue("Nome fornecedor");
	        cell.setCellStyle(headerCellStyle);

	        
	        // Creating data rows for each customer
	        for(int i = 0; i < provisoes.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(provisoes.get(i).getId());
	        	dataRow.createCell(1).setCellValue(provisoes.get(i).getSituacao());
	        	dataRow.createCell(3).setCellValue(Java8DateUtil.getLocalDateFormater(provisoes.get(i).getData()));
	        	dataRow.createCell(2).setCellValue(provisoes.get(i).getNumeroDocumento());
	        	dataRow.createCell(5).setCellValue(provisoes.get(i).getComplemento());
	        	dataRow.createCell(9).setCellValue(provisoes.get(i).getDescricaoFornecedor());
	        	dataRow.createCell(6).setCellValue(provisoes.get(i).getCategoria());
	        	dataRow.createCell(4).setCellValue(provisoes.get(i).getValor());
	        	dataRow.createCell(7).setCellValue(provisoes.get(i).getCentroCusto());
	        	dataRow.createCell(8).setCellValue(provisoes.get(i).getGrupoContas());
	        	dataRow.createCell(10).setCellValue(provisoes.get(i).getNomeFornecedor());
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
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProvisionamentoNaoGeradoException("Erro ao gerar o arquivo. " + ex.getMessage());
		}
	}
}
