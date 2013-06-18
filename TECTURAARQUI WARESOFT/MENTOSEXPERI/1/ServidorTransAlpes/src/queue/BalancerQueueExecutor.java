package queue;

import Repartidor.Repartidor;

import com.test.monitoring.Monitor;
import com.test.monitoring.Trace;
import com.test.queue.QueueExecutor;

/**
 * @class ThreadQueueExecutor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class BalancerQueueExecutor implements QueueExecutor {

	private Repartidor repartidor;

	public BalancerQueueExecutor() {
		repartidor = new Repartidor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.queue.QueueExecutor#execute(com.test.monitoring.TransAlpesMonitor
	 * )
	 */
	@Override
	public void execute(Trace monitor) {
		repartidor.repartirMensaje(monitor.getData());
        monitor.addEndTrace(System.nanoTime());
        writeTraces(monitor.getData()[0]);
	}

	/**
     * @param recibirPaquete
     */
    private void writeTraces(byte data) {
        if (data == 127) {
            Monitor.getInstance().printTraces();
        }
    }
}
