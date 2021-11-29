package Save.O.Save.O.Data.Storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String description;
    private BigDecimal price;
    private LocalDate date;
    private ImageDTO imageDTO;
    private Long categoryId;

}
