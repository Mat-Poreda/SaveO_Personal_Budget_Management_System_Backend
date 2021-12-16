package Save.O.Save.O.Data.Storage.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {


    @OneToOne
    @JoinColumn(name = "imageId")
    Image image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal price;
    private LocalDate date;

    public Transaction(String description, BigDecimal price, LocalDate date) {
        this.description = description;
        this.price = price;
        this.date = date;
    }
}
