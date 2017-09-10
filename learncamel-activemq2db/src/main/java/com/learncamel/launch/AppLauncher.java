package com.learncamel.launch;

import com.learncamel.route.jms2jdbc.Jms2DBRoute;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class AppLauncher {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        String url = "jdbc:postgresql://localhost:5432/localdb";
        main.bind("myDataSource", setUpDataDource(url));
        main.addRouteBuilder(new Jms2DBRoute());
        main.run();
    }

    private static DataSource setUpDataDource(String url) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("root");
        ds.setUrl(url);
        return ds;

    }
}
