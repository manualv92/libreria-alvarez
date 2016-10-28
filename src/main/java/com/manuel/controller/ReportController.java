package com.manuel.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.manuel.model.Compra;
import com.manuel.model.Venta;
import com.manuel.service.CompraService;
import com.manuel.service.VentaService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/report")
public class ReportController {
    @Inject
    VentaService ventaService;
    @Inject
    CompraService compraService;

    @RequestMapping(
            value="/sell", method = RequestMethod.GET
    )
    public void createReport(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            String filename = "E:/ReporteVenta-"+fecha+".xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Ventas de Libreria");

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nro de Venta");
            rowhead.createCell(1).setCellValue("Fecha");
            rowhead.createCell(2).setCellValue("Cliente");
            rowhead.createCell(3).setCellValue("Usuario");
            rowhead.createCell(4).setCellValue("Total");


            List<Venta> ventas = ventaService.getAll();
            int cont = 1;
            for (Venta venta : ventas) {
                HSSFRow row = sheet.createRow((short)cont);
                row.createCell(0).setCellValue(String.valueOf(venta.getId()));
                row.createCell(1).setCellValue(String.valueOf(venta.getFecha()));
                row.createCell(2).setCellValue(String.valueOf(venta.getCliente().getNombre()));
                row.createCell(3).setCellValue(String.valueOf(venta.getUsuario().getNombre()));
                row.createCell(4).setCellValue(venta.getTotal());
                cont++;
            }


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Se creó un archivo excel!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }

    @RequestMapping(
            value="/buy", method = RequestMethod.GET
    )
    public void createBuyReport(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            String filename = "E:/ReporteCompra-"+fecha+".xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Ventas de Libreria");

            HSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);

            HSSFRow rowhead = sheet.createRow((short)0);
            HSSFCell cell0 = rowhead.createCell(0);
            cell0.setCellValue("Nro de Venta");
            cell0.setCellStyle(style);
            HSSFCell cell1 = rowhead.createCell(1);
            cell1.setCellValue("Fecha");
            cell1.setCellStyle(style);
            HSSFCell cell2 = rowhead.createCell(2);
            cell2.setCellValue("Proveeedor");
            cell2.setCellStyle(style);
            HSSFCell cell3 = rowhead.createCell(3);
            cell3.setCellValue("Total");
            cell3.setCellStyle(style);

            List<Compra> compras = compraService.getAll();
            int cont = 1;
            for (Compra compra : compras) {
                HSSFRow row = sheet.createRow((short)cont);
                row.createCell(0).setCellValue(String.valueOf(compra.getId()));
                row.createCell(1).setCellValue(String.valueOf(compra.getFecha()));
                row.createCell(2).setCellValue(String.valueOf(compra.getProveedor().getNombre()));
                row.createCell(3).setCellValue(compra.getTotal());
                cont++;
            }


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Se creó un archivo excel!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
}
