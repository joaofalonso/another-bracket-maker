package com.alonso.abm.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlayerBuilderTest {


    private PlayerBuilder builder;

    @BeforeEach
    public void init(){
        this.builder = new PlayerBuilder();
    }

    @Test
    public void createPlayerSuccessful(){
        String firstName = "player";
        String lastName = "one";
        String nickName = "p1";
        String email = "p1@player.com";
        LocalDate dateOfBirth = LocalDate.now().minusYears(18);
        Player player = this.builder
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .build();

        assertEquals(firstName, player.getFirstName());
        assertEquals(lastName, player.getLastName());
        assertEquals(nickName, player.getNickName());
        assertEquals(email, player.getEmail());
        assertEquals(dateOfBirth, player.getDateOfBirth());
    }

    @Test
    public void createPlayerEmptyEmailException(){
        String firstName = "player";
        String lastName = "one";
        String nickName = "p1";

        LocalDate dateOfBirth = LocalDate.now().minusYears(18);
        this.builder = this.builder
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .dateOfBirth(dateOfBirth);

        assertThrows(RuntimeException.class,
                () -> this.builder.build(),
                "Player email can't be empty!");
    }

    @Test
    public void createPlayerInvalidFormatEmailException(){

        assertThrows(RuntimeException.class,
                () -> this.builder.email("invalidemail@"),
                "Invalid email format!");

    }

    @Test
    public void invalidDateOfBirth(){
        assertThrows(RuntimeException.class,
                () -> this.builder.dateOfBirth(LocalDate.now().plusDays(1)),
                "Invalid date of birth");
    }
}
