package customer.project.service;

import customer.project.dao.CustomerDao;
import customer.project.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer create(Customer customer) {
        return customerDao.create(customer);
    }

    @Override
    public Customer get(Long id) {
        return customerDao.get(id).orElseThrow();
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public boolean delete(Long id) {
        return customerDao.delete(id);
    }
}
