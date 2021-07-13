package customer.project.model;

import customer.project.lib.Phone;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class CustomerRequestDto {
    @Size(min = 2)
    private String fullName;
    @Email
    @Size(min = 2)
    private String email;
    @Phone
    private String phone;
}
