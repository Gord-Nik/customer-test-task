package customer.project.service.dto.mapping;

import customer.project.model.Customer;
import customer.project.model.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponseMapper
        implements DtoResponseMapper<CustomerResponseDto, Customer> {
    @Override
    public CustomerResponseDto toDto(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setFullName(customer.getFullName());
        customerResponseDto.setPhone(customer.getPhone());
        return customerResponseDto;
    }
}
