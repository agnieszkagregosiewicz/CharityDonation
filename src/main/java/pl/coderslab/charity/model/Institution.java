package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="istitutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
