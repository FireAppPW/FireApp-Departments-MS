package pw.ersms.departments.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {

    @Spy
    private ModelMapper modelMapper;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;
    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        department = new Department(1L, "x", "x", "x", "x", "x", "x", "x", "x", false);
    }

    @Test
    void getAllExistedDepartments() {
        when(departmentRepository.findAllExist()).thenReturn(Arrays.asList(department));
        assertNotNull(departmentService.getAllExistedDepartments());
    }

    @Test
    void getDepartmentById() {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.ofNullable(department));
        assertNotNull(departmentService.getDepartmentById(department.getId()));
    }

    @Test
    void createDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        assertNotNull(departmentService.createDepartment(department));
    }

    @Test
    void deleteDepartment() {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.ofNullable(department));
        assertNotNull(departmentService.deleteDepartment(department.getId()));
    }

    @Test
    void updateDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        assertNotNull(departmentService.updateDepartment(department.getId(), department));
    }
}