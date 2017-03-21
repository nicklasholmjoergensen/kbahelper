package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.Sample;

public interface SampleRepository {

    List<Sample> fetchAll();
    List<Sample> fetchAllFromId(Long id);
    Sample fetchObject(Long id);
    int save(Sample sample);
    void delete(Long id);

}
