package pw.ersms.departments.department;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @Id
    Long id;
    String name;
    String addressLine1;
    String addressLine2;
    String city;
    String country;
    String phone;
    String email;
    String logoPicture;
}
