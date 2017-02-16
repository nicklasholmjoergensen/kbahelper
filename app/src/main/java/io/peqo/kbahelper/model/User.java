package io.peqo.kbahelper.model;

/**
 * Class modeling a user.
 */

public class User {

    private final int id;
    private final String firstName;
    private final String lastName;

    private User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
    }

    public static class UserBuilder {
        private final int id;
        private final String firstName;
        private final String lastName;

        public UserBuilder(int id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public User build() {
            return new User(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
