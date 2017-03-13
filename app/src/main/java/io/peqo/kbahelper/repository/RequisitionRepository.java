package io.peqo.kbahelper.repository;

import java.util.List;

import io.peqo.kbahelper.model.Requisition;

/**
 * Contract for RequsitionRepository.
 */

interface RequisitionRepository {

    List<Requisition> findAll();
    Requisition findOne(Long id);
    void save(Requisition requisition);
    void delete(Long id);

}
