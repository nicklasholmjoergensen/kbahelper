package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Sample;

public interface SampleRepository {

    List<Sample> fetchAll();
    Sample fetchObject(Long id);
    void save(Sample sample);
    void delete(Long id);

}
