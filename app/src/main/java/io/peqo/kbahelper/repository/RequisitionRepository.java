package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Requisition;

/**
 * Contract for RequsitionRepository.
 */

interface RequisitionRepository {

    List<Requisition> fetchAll();
    Requisition fetchObject(Long id);
    void save(Requisition requisition);
    void delete(Long id);

}
