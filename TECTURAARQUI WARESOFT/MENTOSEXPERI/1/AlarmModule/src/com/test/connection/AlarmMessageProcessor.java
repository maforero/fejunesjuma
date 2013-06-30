package com.test.connection;

import com.test.dto.AlarmFrameDTO;
import com.test.frame.FrameDemultiplexer;
import com.test.module.AlarmModule;
import com.test.monitoring.Monitor;
import com.test.monitoring.Trace;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class AlarmMessageProcessor implements MessageProcessor {

	private AlarmModule alarmModule;
	private FrameDemultiplexer frmDemultiplexer;

	public AlarmMessageProcessor() {
		alarmModule = new AlarmModule();
		frmDemultiplexer = new FrameDemultiplexer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.connection.MessageProcessor#processMessage(com.test.monitoring
	 * .TransAlpesMonitor)
	 */
	@Override
	public void processMessage(Trace monitor) {

		if (monitor.getData()[0] == 127) {
			Monitor.getInstance().printTraces();
		} else {
			AlarmFrameDTO alarmDTO = frmDemultiplexer
					.demultiplexAlarmFrame(monitor.getData());
			monitor.addTime(System.nanoTime());
			alarmModule.doSomething(alarmDTO);
		}
	}
}
