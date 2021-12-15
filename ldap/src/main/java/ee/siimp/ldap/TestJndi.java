package ee.siimp.ldap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestJndi {
    public static void main(String[] args) throws NamingException {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        String uri = "ldap://0.0.0.0:636/a" ;
        Context ctx = new InitialContext();
        ctx.lookup(uri);
    }
}
