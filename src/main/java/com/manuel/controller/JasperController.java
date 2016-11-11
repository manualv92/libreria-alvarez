package com.manuel.controller;


import com.manuel.model.DetalleVenta;
import com.manuel.model.Venta;
import com.manuel.service.CompraService;
import com.manuel.service.VentaService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.inject.Inject;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/jasper")
public class JasperController {

    HttpHeaders headers = new HttpHeaders();

    @Inject
    VentaService ventaService;

    @RequestMapping(
            value="/create", method = RequestMethod.GET
    )
    public ResponseEntity createJasper(Venta venta) {
        try {
            // Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager
                    .compileReport("E:/jasperinput/report1.jrxml");
            JasperReport jasperReport2 = JasperCompileManager
                    .compileReport("E:/jasperinput/report2.jrxml");

            System.out.println(venta.getFecha());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date(venta.getFecha());
            String fecha = dateFormat.format(date);


            // Parameters for report
            Map parameters = new HashMap<>();
            parameters.put("Fecha", fecha);
            parameters.put("Id", String.valueOf(venta.getId()));
            parameters.put("NombreCliente", String.valueOf(venta.getCliente().getNombre()+ " " + venta.getCliente().getApellido()));
            parameters.put("DomicilioCliente", String.valueOf(venta.getCliente().getDomicilio()));
            parameters.put("CuitCliente", String.valueOf(venta.getCliente().getNroCuit()));


            ArrayList<DetalleVenta> detalleVentasArray = new ArrayList<DetalleVenta>();
            Set<DetalleVenta> detalleVentas = venta.getDetalleVentas();

            for(DetalleVenta d : detalleVentas){
                detalleVentasArray.add(d);
            }

            JRBeanCollectionDataSource beanColDataSource =
                    new JRBeanCollectionDataSource(detalleVentasArray);
            JRBeanCollectionDataSource beanColDataSource2 =
                    new JRBeanCollectionDataSource(detalleVentasArray);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, beanColDataSource);
            JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2,
                    parameters, beanColDataSource2);

            // Make sure the output directory exists.
            File outDir = new File("E:/jasperoutput");
            outDir.mkdirs();

            SimpleDateFormat dateFormatPDF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date datePDF = new Date();
            String fechaPDF = dateFormatPDF.format(datePDF);

            // Export to PDF.
            JasperExportManager.exportReportToPdfFile(jasperPrint,
                    "E:/jasperoutput/Venta "+fechaPDF+".pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint2,
                    "E:/jasperoutput/Factura "+fechaPDF+".pdf");

            System.out.println("Done!");

        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("{\"success\": \"true\"}", headers, HttpStatus.OK);
    }
}
