package com.example.utils;

import java.util.ArrayList;
import java.util.List;

public class BackendServers {
    private static List<String> servers = new ArrayList<>();

    private static int count = 0;

    static {
        servers.add("157.33.19.72");
        servers.add("103.162.74.114");
    }

    public static String getHost() {
        String host = servers.get(count % servers.size());
        count++;
        return host;
    }

}