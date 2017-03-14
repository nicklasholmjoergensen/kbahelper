package io.peqo.kbahelper.repository.contract;


import java.util.List;

import io.peqo.kbahelper.model.Patient;

public interface PatientRepository {

    List<Patient> fetchAll();
    Patient fetchObject(Long id);
    void save(Patient patient);
    void delete(Long id);

}
