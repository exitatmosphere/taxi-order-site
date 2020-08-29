package com.example.taxi.repos;

import com.example.taxi.domain.Message;
import com.example.taxi.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message findById(int id);
    List<Message> findByAuthor(User author);
}
