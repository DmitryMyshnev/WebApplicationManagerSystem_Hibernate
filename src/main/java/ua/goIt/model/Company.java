package ua.goIt.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company implements Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name")
    private String name;
    @Column(name = "quantity_employee")
    private Integer quantityEmployee;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "company_project",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Project> projects = new ArrayList<>();


    @Override
    public Long getId() {
        return id;
    }

}
