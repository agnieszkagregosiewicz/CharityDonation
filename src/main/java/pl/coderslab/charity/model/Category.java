package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="catogories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
