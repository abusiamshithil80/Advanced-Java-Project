package Service;

import Domain.Cashier;
import Repository.CashierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierService {

    private final CashierRepository cashierRepository;

    public CashierService(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    public List<Cashier> getAll(){
        return cashierRepository.getAll();
    }

    public Cashier getById(Long cashierId){
        return cashierRepository.getById(cashierId);
    }

    public int save(Cashier cashier){
        return cashierRepository.save(cashier);
    }

    public int update(Cashier cashier){
        return cashierRepository.update(cashier);
    }

    public int delete(Long cashierId){
        return cashierRepository.delete(cashierId);
    }
}
