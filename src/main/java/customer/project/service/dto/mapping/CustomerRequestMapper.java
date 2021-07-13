package customer.project.service.dto.mapping;

import customer.project.model.Customer;
import customer.project.model.CustomerRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper implements DtoRequestMapper<CustomerRequestDto, Customer> {
    @Override
    public Customer fromDto(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        return customer;
    }
}
