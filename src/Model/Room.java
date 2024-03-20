package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(updatable = false, nullable = false)
    private int roomNumber;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private boolean isRoomReserved;

    public Room(int roomNumber , String type , double price , String imageUrl , boolean isRoomReserved) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isRoomReserved = isRoomReserved;
    }
}
