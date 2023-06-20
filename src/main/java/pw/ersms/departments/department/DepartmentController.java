package pw.ersms.departments.department;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
@SecurityRequirement(name = "Bearer Authentication")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(Principal principal) {
        //get first element of userDetails.getAuthorities()
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        //check if getAuthorities() returns a list of size 2
        if (userDetails.getAuthorities().size() != 2) {
            throw new BadCredentialsException("Invalid credentials");
        }

        //departmentId
        System.out.println(userDetails.getAuthorities().toArray()[0]);
        Long departmentId = Long.parseLong(userDetails.getAuthorities().toArray()[0].toString());

        //role
        System.out.println(userDetails.getAuthorities().toArray()[1]);
        String role = userDetails.getAuthorities().toArray()[1].toString();

        if (role.equals("SysAdmin")) {
            return ResponseEntity.ok().body(departmentService.get());
        } else if (role.equals("FireAdmin")) {
            return ResponseEntity.ok().body(departmentService.get(departmentId));
        } else {
            //return unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.deleteDepartment(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }
}
