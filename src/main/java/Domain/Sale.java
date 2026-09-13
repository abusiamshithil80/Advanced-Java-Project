package Domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private Long saleId;
    private String cashierUsername;
    private Long customerId;
    private LocalDateTime saleDate;
    private Float totalAmount;

    @Valid
    @NotEmpty(message = "A sale must contain at least one product")
    private List<SaleItem> items = new ArrayList<>();

    public Sale() {
    }

    public Sale(Long saleId, String cashierUsername, Long customerId,
                LocalDateTime saleDate, Float totalAmount) {
        this.saleId = saleId;
        this.cashierUsername = cashierUsername;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getCashierUsername() {
        return cashierUsername;
    }

    public void setCashierUsername(String cashierUsername) {
        this.cashierUsername = cashierUsername;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", cashierUsername='" + cashierUsername + '\'' +
                ", customerId=" + customerId +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                ", items=" + items +
                '}';
    }
}
