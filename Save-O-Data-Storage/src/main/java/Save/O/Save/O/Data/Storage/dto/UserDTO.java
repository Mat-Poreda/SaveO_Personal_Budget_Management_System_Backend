package Save.O.Save.O.Data.Storage.dto;

import Save.O.Save.O.Data.Storage.dao.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private Set<Category> categories;
}
