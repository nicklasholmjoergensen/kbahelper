package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Requestor;

public interface RequestorRepository {

    List<Requestor> fetchAll();
    Requestor fetchObject(Long id);
    void save(Requestor requestor);
    void delete(Long id);

}
