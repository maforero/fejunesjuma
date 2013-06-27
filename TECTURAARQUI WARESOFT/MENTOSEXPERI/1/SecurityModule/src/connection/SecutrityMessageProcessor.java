package connection;

/**
 * @class SecutrityQueueExecutor
 * @author GaudiSolutions
 * @Date Jun 23, 2013
 * @since 1.0
 */

import java.util.Arrays;

import IntegrityMonitor.IntegrityMonitor;

import com.test.connection.MessageProcessor;
import com.test.monitoring.Trace;

public class SecutrityMessageProcessor implements MessageProcessor {

	private IntegrityMonitor Integridad;

	public SecutrityMessageProcessor() {
		Integridad = new IntegrityMonitor();
	}

	@Override
	public void processMessage(Trace monitor) {
		byte[] data = monitor.getData();
//		byte realData[] = Arrays.copyOfRange(data, 0, data.length - 1 - data[data.length - 1]);
		monitor.setData(data);
		Integridad.validarMensaje(monitor);
	}
}
