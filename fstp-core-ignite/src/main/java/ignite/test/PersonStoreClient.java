package ignite.test;
  
import org.apache.ignite.Ignite;  
import org.apache.ignite.IgniteCache;  
import org.apache.ignite.IgniteException;  
import org.apache.ignite.Ignition;  
import org.apache.ignite.cache.query.QueryCursor;  
import org.apache.ignite.cache.query.SqlFieldsQuery;  
  
import java.util.List;

public class PersonStoreClient {
    public static void main(String[] args) throws IgniteException {  
        Ignition.setClientMode(true);  
        Ignite ignite = Ignition.start("config\\ignite.xml");  
  
  
        IgniteCache<Long, Person> cache = ignite.getOrCreateCache("personCache");  
        Person p=new Person();  
//        cache.put(p.id,p);
        cache.loadCache(null);  
        QueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery("select id, orgId,name from Person "));  
        System.out.println(cursor.getAll());  
//        Person p=new Person(44,5,"CCED");  
//        cache.put(p.getId(),p);  
  
        ignite.close();  
    } 
}
