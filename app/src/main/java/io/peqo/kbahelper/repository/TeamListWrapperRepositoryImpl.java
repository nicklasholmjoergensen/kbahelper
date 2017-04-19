package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.TeamListWrapper;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.TeamListWrapperRepository;

public class TeamListWrapperRepositoryImpl implements TeamListWrapperRepository {

    private final String URL = "http://207.154.199.94/api/team_list";

    @Override
    public List<TeamListWrapper> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<TeamListWrapper>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }
}
