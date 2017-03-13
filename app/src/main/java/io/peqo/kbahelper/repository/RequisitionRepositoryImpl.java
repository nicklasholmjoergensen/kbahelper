package io.peqo.kbahelper.repository;

import java.util.List;

import javax.inject.Inject;

import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.network.ApiConnection;

/**
 * Concrete implementation of RequistionRepository.
 */

public class RequisitionRepositoryImpl implements RequisitionRepository {

    private final ApiConnection apiConnection;

    @Inject
    public RequisitionRepositoryImpl(ApiConnection apiConnection) {
        this.apiConnection = apiConnection;
    }

    @Override
    public List<Requisition> findAll() {
        return null;
    }

    @Override
    public Requisition findOne(Long id) {
        return null;
    }

    @Override
    public void save(Requisition requisition) {

    }

    @Override
    public void delete(Long id) {

    }
}
