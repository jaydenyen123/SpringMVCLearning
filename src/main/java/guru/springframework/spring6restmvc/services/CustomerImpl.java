package guru.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import guru.springframework.spring6restmvc.model.Customer;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap;

    public CustomerImpl() {

        this.customerMap = new HashMap<>();
        Customer c1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Andy Smith")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer c2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Baxton Yen")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer c3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Barry Tin")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        this.customerMap.put(c1.getId(), c1);
        this.customerMap.put(c2.getId(), c2);
        this.customerMap.put(c3.getId(), c3);
    }


    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(this.customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }
}