package pw.ersms.departments.department;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static pw.ersms.departments.utils.Utils.response;

@Service
@AllArgsConstructor
public class DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final ModelMapper modelMapper;

    private DepartmentDto convertToDto(Department department) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(department, DepartmentDto.class);
    }

    public Object getAllExistedDepartments() {
        List<DepartmentDto> departmentDto = departmentRepository.findAllExist()
                .stream().map(this::convertToDto).toList();
        return response(departmentDto, "Successfully fetch all existing departments");
    }

    public List<Department> get(Integer departmentId){
        return departmentRepository.findAll().stream()
                .filter(department -> department.getId().equals(departmentId))
                .collect(Collectors.toList());
    }

    public Object getDepartmentById(Long id) {
        return response(departmentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No department present with id " + id)
        ), "Successfully get department by id");
    }

    public Object createDepartment(@NotNull Department department) {
        if (department.getName() == null || department.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (department.getAddressLine1() == null || department.getAddressLine1().isEmpty())
            throw new IllegalArgumentException("Address line 1 cannot be null or empty");
        if (department.getCity() == null || department.getCity().isEmpty())
            throw new IllegalArgumentException("City cannot be null or empty");
        if (department.getCountry() == null || department.getCountry().isEmpty())
            throw new IllegalArgumentException("Country cannot be null or empty");
        department.setId(null);
        department.setDeleted(false);
        Department newDepartment = departmentRepository.save(department);
        return response(convertToDto(newDepartment), "Successfully added new department");
    }

    public Object deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No department present with id " + id)
        );
        if (department.isDeleted())
            throw new IllegalArgumentException("Department already deleted");
        department.setDeleted(true);
        departmentRepository.save(department);
        return response(department, "Successfully deleted department");
    }

    public Object updateDepartment(Long id, @NotNull Department department) {
        //if (!departmentRepository.existsById(id))
        //    throw new NoSuchElementException("Not found department with this id");
        if (department.getName() == null || department.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (department.getAddressLine1() == null || department.getAddressLine1().isEmpty())
            throw new IllegalArgumentException("Address line 1 cannot be null or empty");
        if (department.getCity() == null || department.getCity().isEmpty())
            throw new IllegalArgumentException("City cannot be null or empty");
        if (department.getCountry() == null || department.getCountry().isEmpty())
            throw new IllegalArgumentException("Country cannot be null or empty");
        department.setId(id);
        department.setDeleted(false);
        Department newDepartment = departmentRepository.save(department);
        return response(convertToDto(newDepartment), "Successfully updated department");
    }

    public Object getAllDepartments() {
        return response(departmentRepository.findAll(), "Successfully fetch all departments");
    }

    public List<Department> get(){
        return departmentRepository.findAll().stream().toList();
    }
}
