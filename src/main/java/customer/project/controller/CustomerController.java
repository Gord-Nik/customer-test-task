package customer.project.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import customer.project.model.Customer;
import customer.project.model.CustomerRequestDto;
import customer.project.model.CustomerResponseDto;
import customer.project.service.CustomerService;
import customer.project.service.dto.mapping.DtoRequestMapper;
import customer.project.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CustomerController {
    private final CustomerService customerService;
    private final DtoResponseMapper<CustomerResponseDto, Customer> customerDtoResponseMapper;
    private final DtoRequestMapper<CustomerRequestDto, Customer> customerDtoRequestMapper;

    public CustomerController(CustomerService customerService,
                              DtoResponseMapper<CustomerResponseDto,
                                      Customer> customerDtoResponseMapper,
                              DtoRequestMapper<CustomerRequestDto,
                                      Customer> customerDtoRequestMapper) {
        this.customerService = customerService;
        this.customerDtoResponseMapper = customerDtoResponseMapper;
        this.customerDtoRequestMapper = customerDtoRequestMapper;
    }

    @PostMapping
    public CustomerResponseDto addCustomer(@RequestBody @Valid CustomerRequestDto requestDto) {
        Customer customer =
                customerService.create(customerDtoRequestMapper.fromDto(requestDto));
        return customerDtoResponseMapper.toDto(customer);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.get(id);
        return customerDtoResponseMapper.toDto(customer);
    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAll().stream()
                .map(customerDtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Valid CustomerRequestDto customerRequestDto) {
        Customer customer = customerDtoRequestMapper.fromDto(customerRequestDto);
        customer.setId(id);
        customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
