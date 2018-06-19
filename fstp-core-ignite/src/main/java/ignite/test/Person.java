package ignite.test;

public class Person {
	 	private long id;  
	    private int orgId;  
	    private String name;  
	    public Person(){  
	  
	    }  
	    public Person(long vid,int vorgId,String vname){  
	        this.id=vid;  
	        this.orgId=vorgId;  
	        this.name=vname;  
	    }  
	    public long getId() {  
	        return id;  
	    }  
	  
	    public void setId(long id) {  
	        this.id = id;  
	    }  
	  
	    public int getOrgId() {  
	        return orgId;  
	    }  
	  
	    public void setOrgId(int orgId) {  
	        this.orgId = orgId;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
}
