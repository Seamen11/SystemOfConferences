package com.example.systemofconferences.basicSettings;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    @Query("SELECT p FROM Conference p WHERE CONCAT(p.organizer, ' ', p.leader, ' ', p.date_of_start, ' ', " +
            "p.city, ' ', p.date_of_finish) LIKE %?1%")
    List<Conference> search(String keyword);

}