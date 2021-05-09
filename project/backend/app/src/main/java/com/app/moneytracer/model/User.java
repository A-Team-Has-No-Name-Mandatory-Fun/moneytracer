package com.app.moneytracer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="users")
public class User
{
    @Id
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public static class Builder
    {
        private String id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;

        public User build()
        {
            return new User(id, username, password, firstName, lastName, email);
        }

        public Builder withId(String id)
        {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username)
        {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password)
        {
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email)
        {
            this.email = email;
            return this;
        }
    }
}
