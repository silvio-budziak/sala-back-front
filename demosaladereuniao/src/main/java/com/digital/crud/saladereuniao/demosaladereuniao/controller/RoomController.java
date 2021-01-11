package com.digital.crud.saladereuniao.demosaladereuniao.controller;

    import com.digital.crud.saladereuniao.demosaladereuniao.exception.ResourceNotFoundException;
    import com.digital.crud.saladereuniao.demosaladereuniao.model.Room;
    import com.digital.crud.saladereuniao.demosaladereuniao.repository.RoomRepository;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.RequestEntity;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.concurrent.RejectedExecutionException;

    import javax.validation.Valid;

    @RestController @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value="id") long roomId)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
         .orElseThrow(() ->new ResourceNotFoundException("Room not found:: " + roomId));
        return ResponseEntity.ok().body(room);
        }

        @PostMapping("/rooms")
        public Room creatureRoom(@Valid @RequestBody Room room){
            return roomRepository.save(room);
        }

        @PutMapping("/rooms/{id}")
        public ResponseEntity<Room> updateRoom(@PathVariable (value= "id") Long roomId,
                                               @Valid @RequestBody Room roomDatails ) throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found tor this Id::"+ roomId));
        room.setName(roomDatails.getName());
        room.setDate(roomDatails.getDate());
        room.setStartHour(roomDatails.getStartHour());
        room.setEndHour(roomDatails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
        }
        @DeleteMapping("/rooms/{id}")
        public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RejectedExecutionException("Room not found for this id:"+ roomId));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
        }
    }

