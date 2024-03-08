package Users;

import lombok.Builder;
import lombok.Getter;

@Builder
public class User {
    Integer Id;
    @Getter
    String name;
    String secondName;
    String address;
    String passportNumber;

    public Boolean isVerified(){
        return address != null && passportNumber != null;
    }
}
