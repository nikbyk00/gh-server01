package com.example.ghserver01.app.autoWorkGh;

import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ReceivingSpace {

    private SpaceRepo spaceRepo;
    private RoomRepo roomRepo;
    private GreenHouseRepo greenHouseRepo;
    public ArrayList<Space> receivingSpace (ArrayList<Space> list){
        Iterable<Space> space = spaceRepo.findAll();
        space.forEach(s -> list.add(s));
        return list;
    }

//    public ArrayList<Optional<Room>> receivingRoom (ArrayList<Space> list){
//        ArrayList<Optional<Room>> roomArrayList = new ArrayList<>();
//        list.stream().forEach(space -> roomArrayList.add(roomRepo.findAll());
//         return roomArrayList;
//    }
//
//    public Optional<GreenHouse> receivingGreenHouse (ArrayList<Optional<Room>> room) {
//        ArrayList<Optional<GreenHouse>> greenHouseList = new ArrayList<>();
//        room.stream().forEach(OptionalRoomIter -> OptionalRoomIter.stream().
//                forEach(roomIter -> gre));
//    }
}

