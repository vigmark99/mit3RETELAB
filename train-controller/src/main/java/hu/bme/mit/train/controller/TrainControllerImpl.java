package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int counter=0;
	private Table<Long, Integer, Integer> tachograph
	  = HashBasedTable.create();

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}
		enforceSpeedLimit();
	}
	public TimerTask task = new TimerTask() {

		public int timerCount=0;
		public void run() {
			followSpeed();
			timerCount++;
			cancel();
		}
	};
	Timer timer= new Timer();
	public TrainControllerImpl(){
		long delay = 1000L;
		timer.scheduleAtFixedRate(task, 0, 500);
	}

	@Override
	public int getReferenceSpeed() {
		counter++;
		return referenceSpeed;
	}
@Override
	public int getCounter()
	{
		return counter;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		followSpeed();
	}
	@Override
	public void tachographStore() {
		tachograph.put(System.currentTimeMillis(),this.step,this.referenceSpeed);
	}
	@Override
	public int tachographCount() {
		return tachograph.size();
	}

}
