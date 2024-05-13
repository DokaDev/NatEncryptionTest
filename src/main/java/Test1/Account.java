package Test1;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String userName;
    private String passwordHash;
    private String salt;
}
