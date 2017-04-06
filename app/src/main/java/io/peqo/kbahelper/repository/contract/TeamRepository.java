package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.Team;

public interface TeamRepository {

    List<Team> fetchAll();
    Team fetchObject(Long id);

}
