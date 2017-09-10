package com.learncamel.route.jdbc;

import org.apache.camel.builder.RouteBuilder;

public class DBPostegresRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("direct:dbInput")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
              .to("log:?level=INFO&showBody=true");

    }
}
