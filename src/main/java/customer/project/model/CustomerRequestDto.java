package customer.project.model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class CustomerRequestDto {
    @Size(min = 2)
    private String fullName;
    @Email
    @Size(min = 2)
    private String email;
    @NumberFormat(pattern = "\\+?\\d*(\\(\\d{3}\\))?\\d*(-\\d+){0,2}")
    private String phone;
}
