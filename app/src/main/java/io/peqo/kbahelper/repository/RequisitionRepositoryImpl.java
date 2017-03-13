package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.network.ApiConnection;

/**
 * Concrete implementation of RequistionRepository.
 */

public class RequisitionRepositoryImpl implements RequisitionRepository {

    private final String URL = "http://207.154.199.94/api/req";

    @Override
    public List<Requisition> findAll() {
        List<Requisition> requisitions = new ArrayList<>();
        try {
            String response = ApiConnection.createGET(URL)
                    .syncRequest();
            JSONArray arr = new JSONArray(response);
            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Requisition req = new Requisition.Builder()
                        .setId(obj.getLong("id"))
                        .setReqNum(obj.getInt("req_num"))
                        .setRunNum(obj.getInt("run_num"))
                        .setStatus(obj.getInt("status"))
                        .setPatientId(obj.getInt("patient_id"))
                        .setRequestorId(obj.getInt("requestor_id"))
                        .build();
                requisitions.add(req);
            }
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return requisitions;
    }

    @Override
    public Requisition findOne(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        //final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //mapper.setDateFormat(df);
        try {
            String response = ApiConnection.createGET(URL + "/" + id)
                    .syncRequest();
            Log.d("DEBUG", response);
            return mapper.readValue(response, Requisition.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Requisition requisition) {

    }

    @Override
    public void delete(Long id) {

    }
}
