package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Department;

public interface DepartmentRepository {

    List<Department> fetchAll();
    Department fetchObject(Long id);
    void save(Department department);
    void delete(Long id);

}
