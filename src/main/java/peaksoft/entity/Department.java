package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_gen"
    )
    @SequenceGenerator(name = "department_gen",
            sequenceName = "department_seq",
            allocationSize = 1
    )
    Long id;
    String name;
    @ManyToMany(mappedBy = "departments", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    List<Doctor> doctors;
}
