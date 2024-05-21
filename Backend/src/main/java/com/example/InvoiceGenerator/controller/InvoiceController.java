package com.example.InvoiceGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.InvoiceGenerator.entity.Invoice;
import com.example.InvoiceGenerator.entity.StringResponse;
import com.example.InvoiceGenerator.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {


    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoicecreation")
    public ResponseEntity<StringResponse> createInvoice(@RequestBody Invoice invoice, @RequestParam Long userId) {
        try {
            Invoice createdInvoice = invoiceService.createInvoice(invoice, userId);
            StringResponse response = new StringResponse();response.setResponse("Invoice created successfully " +  createdInvoice.getId());
            return new ResponseEntity<> (response, HttpStatus.OK);
        } catch (Exception e) {
          
            StringResponse response = new StringResponse();response.setResponse("Failed To Create ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoiceupdate")
    public ResponseEntity<StringResponse> updateInvoice(@RequestBody Invoice invoice, @RequestParam Long userId) {
       
        try {
         
        	
            Invoice updatedInvoice = invoiceService.updateInvoice(invoice,userId);
            StringResponse response = new StringResponse();
            response.setResponse("Invoice updated successfully  "+updatedInvoice.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            StringResponse response = new StringResponse();
            response.setResponse("Failed to update ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_invoices")
    public ResponseEntity<?> getInvoices(@RequestParam Long userId) {
        try {
            List<Invoice> invoices = invoiceService.getInvoicesByUserId(userId);
            if (invoices.isEmpty()) {
                return new ResponseEntity<>("No invoices found " + userId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getInvoicesByUser_Id")
    public ResponseEntity<?> getInvoicesByUserId(@RequestParam Long userId) {
        try {
            List<Invoice> invoices = invoiceService.getInvoicesByUserId(userId);
            if (invoices.isEmpty()) {
                return new ResponseEntity<>("No invoices found " + userId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getInvoiceById")
    public ResponseEntity<?> getInvoiceById(@RequestParam Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            if (invoice == null) {
                return new ResponseEntity<>("Invoice not found  " + id , HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_invoice")
    public ResponseEntity<String> invoiceDelete(@RequestParam Long id) {
        try {
            String result = invoiceService.invoiceDelete(id);
            if (result.equals("Delete Successfully")) {
                return new ResponseEntity<>("Invoice deleted successfully" + id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to delete " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}