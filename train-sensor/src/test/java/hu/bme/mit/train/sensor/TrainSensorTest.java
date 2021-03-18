package hu.bme.mit.train.sensor;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TrainSensorTest {


    TrainSensor sensor;
    TrainController controller= Mockito.mock(TrainControllerImpl.class);
    TrainUser user= Mockito.mock(TrainUserImpl.class);

    @Before
    public void before() {
        sensor = new TrainSensorImpl(controller,user);
        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void TestAbsolute1() {
        Mockito.when(controller.getReferenceSpeed()).thenReturn(0);

        sensor.overrideSpeedLimit(-1);
        Mockito.verify(user).setAlarmState(true);

    }
    @Test
    public void TestAbsolute2() {
        Mockito.when(controller.getReferenceSpeed()).thenReturn(500);
        sensor.overrideSpeedLimit(501);
        Mockito.verify(user).setAlarmState(true);
    }
    @Test
    public void TestRelative() {
        Mockito.when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        Mockito.verify(user).setAlarmState(true);
    }
    @Test
    public void TestOk() {

        sensor.overrideSpeedLimit(50);
        Mockito.verify(user, times(0)).setAlarmState(true);

    }
}
