package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer quantity;
    @ManyToMany
    @NotEmpty
    private List<Category> categories;
    @ManyToOne
    @NotNull
    private Institution institution;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    //@Pattern(regexp="[0-9][0-9]-[0-9][0-9][0-9]")
    private String zipCode;
    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;
    private String pickUpComment;

}
