package com.learncamel.route.jms2jdbc;


import com.learncamel.route.exception.ExceptionProcessor;
import com.learncamel.route.jdbc.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

import javax.jms.JMSException;

/**
 * Created by z001qgd on 5/27/17.
 */
public class Jms2DBRoute extends RouteBuilder {



    public void configure() throws Exception {

        onException(PSQLException.class).handled(true).log("Exception While inserting messages.").process(new ExceptionProcessor());

        from("activemq:queue:testQueue")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true");
        //.to("direct:output");

    }
}