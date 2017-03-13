package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Room;
import io.peqo.kbahelper.network.ApiConnection;

public class RoomRepositoryImpl implements RoomRepository {

    private final String URL = "http://207.154.199.94/api/rooms";

    @Override
    public List<Room> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL).syncRequest();
            return mapper.readValue(response, new TypeReference<List<Room>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Room fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Room.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Room room) {

    }

    @Override
    public void delete(Long id) {

    }
}
