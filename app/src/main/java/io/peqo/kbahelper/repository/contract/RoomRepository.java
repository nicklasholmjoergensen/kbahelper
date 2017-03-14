package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.Room;

public interface RoomRepository {

    List<Room> fetchAll();
    Room fetchObject(Long id);
    void save(Room room);
    void delete(Long id);

}
