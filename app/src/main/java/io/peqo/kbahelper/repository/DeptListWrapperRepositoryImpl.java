package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.DeptListWrapper;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.DeptListWrapperRepository;

public class DeptListWrapperRepositoryImpl implements DeptListWrapperRepository {

    private final String URL = "http://207.154.199.94/api/departments_list";

    @Override
    public List<DeptListWrapper> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<DeptListWrapper>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }
}
