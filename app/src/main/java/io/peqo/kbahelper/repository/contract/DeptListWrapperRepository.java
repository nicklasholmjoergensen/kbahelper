package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.DeptListWrapper;

public interface DeptListWrapperRepository {

    List<DeptListWrapper> fetchAll();

}
