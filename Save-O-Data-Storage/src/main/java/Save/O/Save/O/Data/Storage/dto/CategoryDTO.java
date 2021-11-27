package Save.O.Save.O.Data.Storage.dto;

import Save.O.Save.O.Data.Storage.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private Long userId;
    private Type categoryType;
    private String categoryName;
    private Set<TransactionDTO> transactionDTOS;

}
