package Save.O.Save.O.Data.Storage.dao;

import Save.O.Save.O.Data.Storage.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @ManyToMany(mappedBy = "categories")
    private Set<Styling> styles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CategoryEnum categoryName;
    @OneToMany
    @JoinColumn(name="types")
    private Set<Type> types;


}
