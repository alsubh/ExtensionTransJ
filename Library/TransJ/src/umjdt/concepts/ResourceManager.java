package umjdt.concepts;

import java.util.ArrayList;
import java.util.List;

import umjdt.Events.*;

public class ResourceManager {

	private String name;
	private int rmId;
	private Resource resource;
	private TransactionManager tm;
	private List<Resource> listResource = new ArrayList<>();
	private LockEvent lockEvent;
	
	
	public ResourceManager(){
		
	}
	public ResourceManager(String _name, int _rmId){
		this.name=_name;
		this.rmId=_rmId;
	}
	
	public int registerResource(Resource _resource){
		listResource.add(_resource);
		return listResource.size();
	}
	public int deregisterResource(Resource resource){
		listResource.remove(resource);
		return listResource.size();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public TransactionManager getTm() {
		return tm;
	}
	public void setTm(TransactionManager tm) {
		this.tm = tm;
	}
	public List<Resource> getListResource() {
		return listResource;
	}
	public void setListResource(List<Resource> listResource) {
		this.listResource = listResource;
	}
	public LockEvent getLockEvent() {
		return lockEvent;
	}
	public void setLockEvent(LockEvent lockEvent) {
		this.lockEvent = lockEvent;
	}
}
