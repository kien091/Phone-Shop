package Source.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "phone")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private String description;
    private int price;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneInCart> phoneInCart;
}
