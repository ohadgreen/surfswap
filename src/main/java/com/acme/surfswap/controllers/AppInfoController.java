package com.acme.surfswap.controllers;

import com.acme.surfswap.model.AppInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("api/appstatus")
    public AppInfo appInfo() {
        AppInfo appInfo = new AppInfo();
        appInfo.setName(appName);
        appInfo.setVersion(appVersion);
        appInfo.setReleaseNotes("hello surfers");
        return appInfo;
    }
}