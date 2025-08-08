package org.example.schedule_develop.Repository;

import org.example.schedule_develop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
