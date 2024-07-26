package com.erico.ceu.lavaceu.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class ControllerUtils {

    private ControllerUtils() {
    }

    public static URI buildResourceLocationUri(String path, Object resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(resourceId).toUri();
    }

}
