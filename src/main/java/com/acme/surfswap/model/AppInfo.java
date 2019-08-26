package com.acme.surfswap.model;

import lombok.Data;

@Data
public class AppInfo {
    private String name;
    private String version;
    private String releaseNotes;

    public AppInfo() {
    }
}
