package Save.O.Save.O.Data.Storage.dao;

import Save.O.Save.O.Data.Storage.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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


    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name="category_id")
    private Set<Transaction> transactions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
    private String name;

    public Category(Type type, String name) {
        this.type=type;
        this.name=name;

    }
}
