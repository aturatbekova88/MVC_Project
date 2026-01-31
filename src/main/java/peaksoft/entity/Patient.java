package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import peaksoft.enums.Gender;

import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_gen"
    )
    @SequenceGenerator(name = "patient_gen",
            sequenceName = "patient_seq",
            allocationSize = 1
    )
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String email;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    Hospital hospital;
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    List<Appointment> appointments;
}
