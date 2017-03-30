package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;

public interface RequisitionListWrapperRepository {

    List<RequisitionListWrapper> fetchAll();
    List<RequisitionListWrapper> fetchAllFinished();
    List<RequisitionListWrapper> fetchAllFromUser(Long id);
    RequisitionListWrapper fetchObject(Long id);

}
