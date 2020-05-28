package org.firstinspires.ftc.teamcode._Archive.Sensor;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class IMU extends ValueSensor
{
    // References
    private BNO055IMU imu;
    OpMode opmode;

    // Variables
    Orientation angles;
    Acceleration acceleration;

    public IMU(OpMode opMode)
    {
        this.opmode = opMode;
    }

    public Orientation GetAngles()
    {
        return angles;
    }

    public Acceleration GetAcceleration()
    {
        return acceleration;
    }

    @Override
    public void Init()
    {
        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = opmode.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    @Override
    public void Start()
    {
        // Init the logging of measured acceleration
        imu.startAccelerationIntegration(new Position(), new Velocity(), 150000);//was 1000
    }

    @Override
    public void Loop()
    {
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        acceleration = imu.getAcceleration();
    }
}
