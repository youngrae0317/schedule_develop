package org.example.schedule_develop.Repository;

import org.example.schedule_develop.Entity.Schedule;
import org.example.schedule_develop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, id + "아이디가 존재하지 않습니다. + "));
    }

    Optional<User> findByEmail(String email);

}
