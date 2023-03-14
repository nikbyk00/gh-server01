package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepo extends CrudRepository<Room, Integer> {
}
