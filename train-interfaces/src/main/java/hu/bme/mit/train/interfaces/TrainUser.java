package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	boolean getAlarmFlag();

	void setAlarmState(boolean state);
	boolean getAlarmState();

	void overrideJoystickPosition(int joystickPosition);

}
