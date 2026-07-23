package com.rakuten.bank;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    // 空のクラスでOK。これがあるだけでJAX-RSが有効になります
}