package com.xhmata.event.service.implemetation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhmata.event.model.Event;
import com.xhmata.event.model.Transaction;
import com.xhmata.event.repo.EventRepo;
import com.xhmata.event.repo.TransactionRepo;
import com.xhmata.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private final EventRepo eventRepo;

    @Autowired
    private final TransactionRepo transactionRepo;

    @Autowired
    private ObjectMapper mapper;

//    private final RabbitMQMessageProducer producer;

    @Override
    public List<Event> allEvents() {
        return eventRepo.findAll();
    }

    @Override
    public Event create(Event event) {
        log.info("Saving new event: {}", event.getName());
        return eventRepo.save(event);
    }

    @Override
    public Collection<Event> list(int limit) {
        log.info("Fetching all events");
        return eventRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Event get(Long id) {
        log.info("Fetching events by id: {}", id);
        return eventRepo.findById(id).get();
    }

    @Override
    public Event update(Event item) {
        log.info("Updating item: {}", item.getName());
        return eventRepo.save(item);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting item by ID: {}", id);
        eventRepo.deleteById(id);
        return TRUE;
    }
    @Override
    public List<Transaction> findTransactionsOfUsers(Long userId){
        return transactionRepo.findAllByUserId(userId);
    }


    @Override
    public List<Transaction> findTransactionsOfItem(Long eventId) {
        return transactionRepo.findAllByEventId(eventId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }
}
