package io.peqo.kbahelper.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void create_User() {
        User user = new User.UserBuilder(1, "Nicklas", "Holm")
                .build();
        assertEquals(1, user.getId());
        assertEquals("Nicklas", user.getFirstName());
    }
}
