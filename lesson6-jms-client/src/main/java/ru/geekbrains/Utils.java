package ru.geekbrains;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public final class Utils {

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(JMSClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
