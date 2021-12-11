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
    private Type type;
    private String name;
    private String icon;
    private Set<TransactionDTO> transactions;
    private double  sum;
    private double count;
    private double avg;


}
