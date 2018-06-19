package ignite.test;

import org.apache.ignite.cache.store.CacheStore;  
import org.apache.ignite.lang.IgniteBiInClosure;  
import org.apache.ignite.resources.SpringResource;  
import org.jetbrains.annotations.Nullable;  
  
import javax.cache.Cache;  
import javax.cache.integration.CacheLoaderException;  
import javax.cache.integration.CacheWriterException;  
import javax.sql.DataSource;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.Collection;  
import java.util.Map;  
public class PersonStore implements CacheStore<Long, Person>{
	 @SpringResource(resourceName = "dataSource")  
	    private DataSource dataSource;  
	  
	    @Override  
	    public void loadCache(IgniteBiInClosure<Long, Person> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {  
	        System.out.println(">> Loading cache from store...");  
	  
	        try {  
	            Connection conn = dataSource.getConnection();  
	            PreparedStatement st = conn.prepareStatement("select * from PERSON");  
	            ResultSet rs = st.executeQuery();  
	             while (rs.next()) {  
	                 System.out.println("----------------->"+rs.getString(3));  
	                 Person person = new Person(rs.getLong(1),  rs.getInt(2), rs.getString(3));  
	                 igniteBiInClosure.apply(person.getId(), person);  
	             }  
	  
	         } catch (Exception e) {  
	            throw new CacheLoaderException("Failed to load values from cache store.", e);  
	  
	        }  
	  
	    }  
	  
	    @Override  
	    public void sessionEnd(boolean b) throws CacheWriterException {  
	  
	    }  
	  
	    @Override  
	    public Person load(Long aLong) throws CacheLoaderException {  
	        System.out.println(">> Loading person from store...");  
	        try (Connection conn = dataSource.getConnection()) {  
	            try (PreparedStatement st = conn.prepareStatement("select * from PERSON where id = ?")) {  
	                st.setString(1, aLong.toString());  
	                ResultSet rs = st.executeQuery();  
	                return rs.next() ? new Person(rs.getLong(1), rs.getInt(2), rs.getString(3) ) : null;  
	            }  
	  
	        } catch (SQLException e) {  
	            throw new CacheLoaderException("Failed to load values from cache store.", e);  
	  
	        }  
	    }  
	  
	    @Override  
	    public Map<Long, Person> loadAll(Iterable<? extends Long> iterable) throws CacheLoaderException {  
	        return null;  
	    }  
	  
	    @Override  
	    public void write(Cache.Entry<? extends Long, ? extends Person> entry) throws CacheWriterException {  
	        System.out.println(">> Loading person from store...");  
	        try (Connection conn = dataSource.getConnection()) {  
	            PreparedStatement st = conn.prepareStatement("insert into  PERSON(id,orgId,name)VALUE (?,?,?) ");  
	            st.setLong((int)1,(long)entry.getKey());  
	            st.setInt((int)2,entry.getValue().getOrgId());  
	            st.setString((int)3,entry.getValue().getName());  
	            st.executeUpdate();  
	            System.out.println(">> add person to store...");  
	  
	        } catch (SQLException e) {  
	            throw new CacheLoaderException("Failed to add person  to table store.", e);  
	        }  
	  
	    }  
	  
	    @Override  
	    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends Person>> collection) throws CacheWriterException {  
	  
	    }  
	  
	    @Override  
	    public void delete(Object o) throws CacheWriterException {  
	  
	    }  
	  
	    @Override  
	    public void deleteAll(Collection<?> collection) throws CacheWriterException {  
	  
	    }  
}
