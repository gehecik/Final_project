package org.example.data;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;

    public static User userWithRandomField() {
        String email = RandomStringUtils.randomAlphabetic(5) + "@yandex.ru";
        String password = RandomStringUtils.randomNumeric(10);
        return User.builder()
                .name("name")
                .email(email)
                .password(password)
                .build();
    }

}
