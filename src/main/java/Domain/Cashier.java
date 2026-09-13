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



    public Cashier() {
    }

    public Cashier(Long cashierId, String username, String phone) {
        this.cashierId = cashierId;
        this.username = username;
        this.phone = phone;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "cashierId=" + cashierId +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
