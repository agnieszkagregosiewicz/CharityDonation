package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private Integer quantity;
    @ManyToMany
    @NotEmpty
    private List<Category> categories;
    @ManyToOne
    @NotEmpty
    private Institution institution;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private LocalDate pickUpDate;
    @NotEmpty
    private LocalTime pickUpTime;
    private String pickUpComment;

}
