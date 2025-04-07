package com.gajanan.Task.Manager.App.util;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String jwt) {
}
