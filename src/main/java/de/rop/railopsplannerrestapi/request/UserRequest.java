package de.rop.railopsplannerrestapi.request;

import lombok.Data;
import de.rop.railopsplannerrestapi.entity.User;

@Data
public class UserRequest {
    User user;
}
