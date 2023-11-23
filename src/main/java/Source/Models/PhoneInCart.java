package Source.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_phone")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PhoneInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private int quantity;
}
