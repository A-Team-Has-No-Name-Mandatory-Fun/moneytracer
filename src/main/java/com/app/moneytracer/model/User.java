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

    private String name;

    public static class Builder
    {
        private String name;
        private String id;

        public User build()
        {
            return new User(id, name);
        }

        public Builder withName(String name)
        {
            this.name = name;
            return this;
        }

        public Builder withId(String id)
        {
            this.id = id;
            return this;
        }
    }
}
