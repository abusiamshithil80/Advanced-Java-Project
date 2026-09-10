package Domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Cashier {

    private Long cashierId;

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(?:01[3-9]\\d{8}|)$", message = "Enter a valid Bangladeshi mobile number")
    private String phone;

    private LocalDate joiningDate;

    
}
