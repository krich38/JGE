package org.jge.protocol;

import com.esotericsoftware.kryo.Kryo;
import org.jge.model.Id;
import org.jge.model.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Protocol {
    public static void register(Kryo kryo) {


        kryo.register(User.class);
        kryo.register(Id.class);


    }
}
