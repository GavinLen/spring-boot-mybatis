package xyz.ieden.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ieden.mybatis.component.DataSourceComponent;

import javax.sql.DataSource;

/**
 * @author ThinkPad
 * @date Created by 2018/4/20 18:44
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceComponent dataSourceComponent;

    @Bean(name = "druidDataSource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceComponent.getDriverClassName());
        druidDataSource.setUrl(dataSourceComponent.getUrl());
        druidDataSource.setUsername(dataSourceComponent.getUsername());
        druidDataSource.setPassword(dataSourceComponent.getPassword());
        return druidDataSource;
    }

}
