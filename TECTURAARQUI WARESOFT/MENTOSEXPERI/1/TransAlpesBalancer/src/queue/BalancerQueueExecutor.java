package queue;

import Repartidor.Dispatcher;
import Repartidor.DispatcherQueue;
import Repartidor.Node;

import com.test.byteutil.ByteUtils;
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

	private Dispatcher repartidor;

	public BalancerQueueExecutor(DispatcherQueue<Node> dispatcherQueue) {
		repartidor = new Dispatcher(dispatcherQueue);
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
		byte realData[] = monitor.getData();
		byte data[] = ByteUtils.getInstance().getByteWithEnding(realData);
		monitor.setData(data);
		repartidor.repartirMensaje(monitor);
        monitor.addTime(System.nanoTime());
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
