package com.adservio.reservationv2.dao;

import com.adservio.reservationv2.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);

    @Query(value = "select * from room as ro "
            + "where ro.id not in "
            + "("
            + "select re.room_id "
            + "from booking as re "
            + "where (re.start_date BETWEEN ?1 and ?2) "
            + "or (re.end_date BETWEEN ?1 and  ?2) "
            + ")", nativeQuery = true)
    List<Room> findMeetingRoomAvailable(LocalDateTime db, LocalDateTime de);

    @Query(value = "select * from (select * from room as ro where ro.id not in "
            + "(select re.room_id from booking as re where (re.start_date BETWEEN ?1 and  ?2) "
            + "or (re.end_date BETWEEN ?1 and ?2))) as roo where roo.id = ?3", nativeQuery = true)
    Room checkAvailability(LocalDateTime db, LocalDateTime de, Long id);
}