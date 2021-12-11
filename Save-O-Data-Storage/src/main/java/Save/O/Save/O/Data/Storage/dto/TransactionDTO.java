package Save.O.Save.O.Data.Storage.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonAlias("_name")
    public String name;
    @JsonAlias("_id")
    private Long id;
    @JsonAlias("_description")
    private String description;
    @JsonAlias("_price")
    private BigDecimal price;
    @JsonAlias("_date")
    private LocalDate date;
    @JsonAlias("_categoryId")
    private Long categoryId;


}
