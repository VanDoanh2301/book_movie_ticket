package com.test.nmt.model.user;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class UserDto2 {
    private String username;
    private String password;
    private String email;
}
