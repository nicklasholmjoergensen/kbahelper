package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;

public interface RequisitionListWrapperRepository {

    List<RequisitionListWrapper> fetchAll();
    RequisitionListWrapper fetchObject(Long id);
    void save(RequisitionListWrapper wrapper);
    void delete(Long id);

}
