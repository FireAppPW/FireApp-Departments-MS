package pw.ersms.departments.department;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "address_line_1",
            nullable = false
    )
    private String addressLine1;
    @Column(
            name = "address_line_2",
            nullable = true
    )
    private String addressLine2;
    @Column(
            name = "city",
            nullable = false
    )
    private String city;
    @Column(
            name = "country",
            nullable = false
    )
    private String country;
    @Column(
            name = "phone",
            nullable = true
    )
    private String phone;
    @Column(
            name = "email",
            nullable = true
    )
    private String email;
    @Column(
            name = "logo_picture",
            nullable = true
    )
    private String logoPicture;
    @Column(
            name = "is_deleted",
            nullable = false
    )
    private boolean isDeleted;
}
