package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.RequisitionListWrapperRepository;

public class RequisitionListWrapperRepositoryImpl implements RequisitionListWrapperRepository {

    private final String URL = "http://207.154.199.94/api/req_list";

    @Override
    public List<RequisitionListWrapper> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<RequisitionListWrapper>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public RequisitionListWrapper fetchObject(Long id) {
        return null;
    }

    @Override
    public void save(RequisitionListWrapper wrapper) {

    }

    @Override
    public void delete(Long id) {

    }
}
