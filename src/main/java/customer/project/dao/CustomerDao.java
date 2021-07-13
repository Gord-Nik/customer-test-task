package customer.project.dao;

import customer.project.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    Customer create(Customer customer);

    Optional<Customer> get(Long id);

    List<Customer> getAll();

    Customer update(Customer customer);

    boolean delete(Long id);
}
