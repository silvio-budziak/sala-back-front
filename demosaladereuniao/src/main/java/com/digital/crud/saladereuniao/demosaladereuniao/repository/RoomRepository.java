package com.digital.crud.saladereuniao.demosaladereuniao.repository;

import com.digital.crud.saladereuniao.demosaladereuniao.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {


}
