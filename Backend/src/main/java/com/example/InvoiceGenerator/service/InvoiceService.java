package com.example.InvoiceGenerator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.InvoiceGenerator.entity.Invoice;
import com.example.InvoiceGenerator.entity.User;
import com.example.InvoiceGenerator.repository.InvoiceRepository;
import com.example.InvoiceGenerator.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {
    
	@Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    
    public Invoice createInvoice(Invoice invoice, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
     // Set the current date and time
        invoice.setDate(LocalDateTime.now());
        invoice.setUser(user);
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoice(Invoice invoice, Long userId) {
    	User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: "+userId));
    	invoice.setUser(user);
    	return invoiceRepository.save(invoice);
    	}
    
    public List<Invoice> allInvoice() {
        return invoiceRepository.findAll();
    }
    
    public List<Invoice> getInvoicesByUserId(Long userId) {
        return invoiceRepository.findByUserId(userId); 
    }
    
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
    
    public String invoiceDelete(Long id) {
        invoiceRepository.deleteById(id);
        return "Delete Successfully";
    }
}