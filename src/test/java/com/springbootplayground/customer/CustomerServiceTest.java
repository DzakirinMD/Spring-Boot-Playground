package com.springbootplayground.customer;

import com.springbootplayground.customer.entity.dto.CustomerDTO;
import com.springbootplayground.customer.entity.Customer;
import com.springbootplayground.customer.entity.CustomerCar;
import com.springbootplayground.customer.entity.mapper.CustomerDTOMapper;
import com.springbootplayground.customer.exception.CustomerResourceNotFoundException;
import com.springbootplayground.customer.repository.CustomerRepository;
import com.springbootplayground.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDTOMapper customerDTOMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Given
        List<Customer> sampleCustomers = new ArrayList<>();
        sampleCustomers.add(new Customer(1L, "frank", "frank@gmail.com", LocalDate.of(1992, APRIL, 3), new CustomerCar("Toyota", "Vios")));
        sampleCustomers.add(new Customer(2L,"milly", "milly@gmail.com", LocalDate.of(1990, JANUARY, 25), new CustomerCar("Toyota", "Vios")));

        List<CustomerDTO> expected = new ArrayList<>();
        expected.add(new CustomerDTO(1L, "frank", "Vios"));
        expected.add(new CustomerDTO(2L, "milly", "Vios"));

        // Given - Mock the behavior of the customerRepository to return the sample customers
        when(customerRepository.findAll()).thenReturn(sampleCustomers);

        // Given - Mock the behavior of the customerDTOMapper to map customers to their DTO representations
        when(customerDTOMapper.apply(any())).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getCustomerCar().getModel());
        });

        // When
        List<CustomerDTO> actual = customerService.getAllCustomers();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCustomer_ValidId() {
        // Given
        Customer sampleCustomer = new Customer("frank", "frank@gmail.com", LocalDate.of(1992, APRIL, 3), new CustomerCar("Toyota", "Vios"));
        CustomerDTO expected = new CustomerDTO(1L, "frank","Vios");

        // Given - Mock the behavior of the customerRepository to return the sample customer
        when(customerRepository.findById(1L)).thenReturn(Optional.of(sampleCustomer));

        // Given -Mock the behavior of the customerDTOMapper to map the customer to its DTO representation
        when(customerDTOMapper.apply(sampleCustomer)).thenReturn(expected);

        // When
        Optional<CustomerDTO> actual = customerService.getCustomer(1L);

        // Then
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testGetCustomer_InvalidId() {
        Long customerId = 999L;

        // Mock the behavior of the customerRepository to return an empty Optional
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Call the method to be tested and expect an exception to be thrown
        assertThrows(CustomerResourceNotFoundException.class, () -> customerService.getCustomer(customerId));
    }
}
