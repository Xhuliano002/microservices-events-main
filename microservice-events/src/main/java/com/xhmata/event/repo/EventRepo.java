package com.xhmata.event.repo;

import com.xhmata.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
    Event findByName(String name);

}
