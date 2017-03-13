package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Department;
import io.peqo.kbahelper.network.ApiConnection;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final String URL = "http://207.154.199.94/api/departments";

    @Override
    public List<Department> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL).syncRequest();
            return mapper.readValue(response, new TypeReference<List<Department>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Department fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Department.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Department department) {

    }

    @Override
    public void delete(Long id) {

    }
}
