package Api;

import Domain.Cashier;
import Service.CashierService;
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
@RequestMapping("/api/cashiers")
public class CashierApi {

    private final CashierService cashierService;

    public CashierApi(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping
    public ResponseEntity<List<Cashier>> getAll() {
        return ResponseEntity.ok(cashierService.getAll());
    }

    @GetMapping("/{cashierId}")
    public ResponseEntity<Cashier> getById(@PathVariable Long cashierId) {
        return ResponseEntity.ok(cashierService.getById(cashierId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Cashier cashier) {
        cashierService.save(cashier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{cashierId}")
    public ResponseEntity<Void> update(@PathVariable Long cashierId, @Valid @RequestBody Cashier cashier) {
        cashier.setCashierId(cashierId);
        cashierService.update(cashier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cashierId}")
    public ResponseEntity<Void> delete(@PathVariable Long cashierId) {
        cashierService.delete(cashierId);
        return ResponseEntity.noContent().build();
    }
}
