package queue;

/**
 * @class SecutrityQueueExecutor
 * @author GaudiSolutions
 * @Date Jun 23, 2013
 * @since 1.0
 */

import IntegrityMonitor.IntegrityMonitor;
import com.test.queue.QueueExecutor;
import com.test.monitoring.Trace;


public class SecutrityQueueExecutor {
	
	public class BalancerQueueExecutor implements QueueExecutor {

		private IntegrityMonitor Integridad;

		public BalancerQueueExecutor() {
			Integridad = new IntegrityMonitor();
		}

		@Override
		public void execute(Trace monitor) {
	       Integridad.validarMensaje(monitor.getData());
		}
	}
}
