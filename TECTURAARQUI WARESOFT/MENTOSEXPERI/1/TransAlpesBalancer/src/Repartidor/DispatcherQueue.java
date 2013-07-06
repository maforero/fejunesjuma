package Repartidor;

import java.util.List;

/**
 * @class DispatcherQueue.java
 * @author Felipe
 * @Date Jul 5, 2013
 * @since 1.0
 */
public interface DispatcherQueue <T>{

	public void add(T t);
	
	public T poll();
	
	public boolean isEmtpy();
	
	public int getSize();
	
	public boolean hasActiveNodes();
	
	public List<T> getValues(); 
}
