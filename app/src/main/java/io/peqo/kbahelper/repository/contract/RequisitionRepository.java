package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.Requisition;

/**
 * Contract for RequsitionRepository.
 */

public interface RequisitionRepository {

    List<Requisition> fetchAll();
    Requisition fetchObject(Long id);
    int save(Requisition requisition);
    int delete(Long id);

}
