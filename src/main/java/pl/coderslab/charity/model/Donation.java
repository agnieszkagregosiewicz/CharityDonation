package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @Min(3)
    private String street;
    @NotEmpty
    @Min(3)
    private String city;
    @NotEmpty
    @Pattern(regexp="\"d{2}-d{3}\"")
    private String zipCode;
    @NotEmpty
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @NotEmpty
    @DateTimeFormat(pattern = "HH-mm")
    private LocalTime pickUpTime;
    private String pickUpComment;

}
