package customer.project.service;

import customer.project.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer get(Long id);

    List<Customer> getAll();

    Customer update(Customer customer);

    boolean delete(Long id);
}
