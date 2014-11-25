
package mealmanager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author SHAFIN
 */
public class InternetConnection {

    /**
     * Checks Internet connection
     * @return
     * @throws IOException
     */
    public boolean checkConnection() throws IOException {

        Socket socketObject = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);

        socketObject.connect(address, 3000);

        return true;
    }
}
