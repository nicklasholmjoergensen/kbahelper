package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.wrapper.TeamListWrapper;

public interface TeamListWrapperRepository {

    List<TeamListWrapper> fetchAll();

}
