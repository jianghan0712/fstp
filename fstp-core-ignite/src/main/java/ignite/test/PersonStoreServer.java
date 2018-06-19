package ignite.test;

import org.apache.ignite.Ignite;  
import org.apache.ignite.IgniteException;  
import org.apache.ignite.Ignition;  

public class PersonStoreServer {
	 public static void main(String[] args) throws IgniteException {  
	        Ignite ignite = Ignition.start("config\\ignite.xml");  
	    } 
}
