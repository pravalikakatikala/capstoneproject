package com.example.InvoiceGenerator.repositorytest;
import com.example.InvoiceGenerator.entity.Invoice;
import com.example.InvoiceGenerator.repository.InvoiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InvoiceRepositoryTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        // Initialize an Invoice object for testing
        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setClientName("Test the Client");
        invoice.setAmount(5000.0);
        invoice.setDescription("Test Description");
    }

    // Positive

    @Test
    public void testFindAllInvoices_Positive() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        Mockito.when(invoiceRepository.findAll()).thenReturn(invoiceList);

        List<Invoice> foundInvoices = invoiceRepository.findAll();

        Assertions.assertEquals(1, foundInvoices.size());
        Assertions.assertEquals(invoice.getId(), foundInvoices.get(0).getId());
       
    }

    @Test
    public void testFindInvoiceById_Positive() {
        Mockito.when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice));

        Optional<Invoice> foundInvoice = invoiceRepository.findById(1L);

        Assertions.assertTrue(foundInvoice.isPresent());
        Assertions.assertEquals(invoice.getId(), foundInvoice.get().getId());
    }

    @Test
    public void testFindByUserId_Positive() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        Mockito.when(invoiceRepository.findByUserId(Mockito.anyLong())).thenReturn(invoiceList);

        List<Invoice> foundInvoices = invoiceRepository.findByUserId(1L);

        Assertions.assertEquals(1, foundInvoices.size());
        Assertions.assertEquals(invoice.getId(), foundInvoices.get(0).getId());
      
    }

    @Test
    public void testSaveInvoice_Positive() {
       
        Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        Assertions.assertNotNull(savedInvoice);
        Assertions.assertEquals(invoice.getId(), savedInvoice.getId());
      
    }

    @Test
    public void testUpdateInvoice_Positive() {
     
        Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        Assertions.assertNotNull(updatedInvoice);
        Assertions.assertEquals(invoice.getId(), updatedInvoice.getId());
       
    }

    @Test
    public void testDeleteInvoice_Positive() {
    
        Mockito.doNothing().when(invoiceRepository).deleteById(Mockito.anyLong());

        invoiceRepository.deleteById(1L);

        Mockito.verify(invoiceRepository, Mockito.times(1)).deleteById(1L);
    }

    // Negative 

    @Test
    public void testFindAllInvoices_Negative() {
      
        Mockito.when(invoiceRepository.findAll()).thenReturn(new ArrayList<>());

        List<Invoice> foundInvoices = invoiceRepository.findAll();

        Assertions.assertTrue(foundInvoices.isEmpty());
    }

    @Test
    public void testFindInvoiceById_Negative() {
       
        Mockito.when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Invoice> foundInvoice = invoiceRepository.findById(2L);

        Assertions.assertTrue(foundInvoice.isEmpty());
    }

    @Test
    public void testFindByUserId_Negative() {
       
        Mockito.when(invoiceRepository.findByUserId(Mockito.anyLong())).thenReturn(new ArrayList<>());

        List<Invoice> foundInvoices = invoiceRepository.findByUserId(2L);

        Assertions.assertTrue(foundInvoices.isEmpty());
    }

    @Test
    public void testSaveInvoice_ConstraintViolation_Negative() {
        
        Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenThrow(javax.validation.ConstraintViolationException.class);

        Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> invoiceRepository.save(invoice));
    }

    @Test
    public void testUpdateInvoice_ConstraintViolation_Negative() {
      
        Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenThrow(javax.validation.ConstraintViolationException.class);

        Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> invoiceRepository.save(invoice));
    }

    @Test
    public void testDeleteInvoice_Negative() {
      
        Mockito.doThrow(new IllegalArgumentException("No such invoice")).when(invoiceRepository).deleteById(Mockito.anyLong());

        Assertions.assertThrows(IllegalArgumentException.class, () -> invoiceRepository.deleteById(2L));
    }
}