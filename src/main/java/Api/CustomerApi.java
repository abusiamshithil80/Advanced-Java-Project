package Api;

import Domain.Customer;
import Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerApi {

    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{customerId}")
    public Customer getById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getById(customerId)).getBody();
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> update(
            @PathVariable Long customerId,
            @Valid @RequestBody Customer customer
    ) {
        return customerService.update(customerId, customer)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {
        return customerService.delete(customerId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
