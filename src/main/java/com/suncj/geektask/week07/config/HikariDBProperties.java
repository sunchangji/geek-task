package com.suncj.geektask.week07.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hikari")
public class HikariDBProperties {

    private HikariDataSource master;

    private HikariDataSource slave;

    public HikariDataSource getMaster() {
        return master;
    }

    public void setMaster(HikariDataSource master) {
        this.master = master;
    }

    public HikariDataSource getSlave() {
        return slave;
    }

    public void setSlave(HikariDataSource slave) {
        this.slave = slave;
    }
}
