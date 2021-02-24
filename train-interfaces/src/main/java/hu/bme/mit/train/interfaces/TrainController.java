package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	int getCounter();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

}
