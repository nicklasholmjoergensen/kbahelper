package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Bed;

public interface BedRepository {

    List<Bed> fetchAll();
    Bed fetchObject(Long id);
    void save(Bed bed);
    void delete(Long id);

}
