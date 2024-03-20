package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") // <-- by default here will be strategy,
    //but since hibernate 6.2 is deprecated, and we need to use type instead
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
