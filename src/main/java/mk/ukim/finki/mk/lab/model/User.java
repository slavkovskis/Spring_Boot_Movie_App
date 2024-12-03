package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.mk.lab.service.impl.PersonNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cinema_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
