package Api;

import Domain.PasswordChange;
import Domain.Sale;
import Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAccountApi {

    private final CustomerService customerService;

    public CustomerAccountApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Sale>> getMyPurchases(Authentication authentication) {
        return ResponseEntity.ok(customerService.getMyPurchases(authentication.getName()));
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody PasswordChange passwordChange,
            Authentication authentication
    ) {
        customerService.updatePassword(authentication.getName(), passwordChange.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
