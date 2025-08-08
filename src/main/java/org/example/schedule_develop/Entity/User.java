package org.example.schedule_develop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.Dto.UserRequestDto;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;

    public User(UserRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.email = requestDto.getEmail();
    }

    public void userUpdate(UserRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.email = requestDto.getEmail();
    }
}
