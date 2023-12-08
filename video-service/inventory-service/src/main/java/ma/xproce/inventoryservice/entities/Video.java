package ma.xproce.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "creator")

public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String description;
    private LocalDate datePublication;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;

}
