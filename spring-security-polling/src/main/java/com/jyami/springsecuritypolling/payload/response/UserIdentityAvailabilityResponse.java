package com.jyami.springsecuritypolling.payload.response;

import lombok.*;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentityAvailabilityResponse {
    private boolean available;
}
