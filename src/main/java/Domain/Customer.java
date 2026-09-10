package Domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {

    private Long customerId;

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(?:01[3-9]\\d{8}|)$", message = "Enter a valid mobile number")
    private String phone;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;


}
