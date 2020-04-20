package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Algorithm.DriveAlgorithm;
import org.firstinspires.ftc.teamcode.Control.ChassisControl;
import org.firstinspires.ftc.teamcode.Sensor.IMU;
import org.firstinspires.ftc.teamcode.External.*;

public abstract class TeleOpMode extends OpMode implements ControllerInputListener
{
    //REFERENCES
    //Default Refs
    protected DriveAlgorithm DriveCalc;
    protected DcMotor[] Motors;

    //Other
    protected ChassisControl RobotChassisControl;
    protected ControllerInputManager InputManager;
    protected IMU RobotIMU;


    //VARIABLES
    //Default Stats
    protected double BaseSpeed = 1;
    protected double BaseOffset = 0;


    @Override
    public abstract void init();
    @Override
    public abstract void start();
    @Override
    public abstract void loop();

    //ManagementFunctions
    public void SetTeleOpStats(DriveAlgorithm setDriveCalc, double setBaseSpeed, double setBaseOffset, DcMotor[] setMotors) {
        DriveCalc = setDriveCalc;
        BaseSpeed = setBaseSpeed;
        BaseOffset = setBaseOffset;
        Motors = setMotors;
    }

    protected void InitTeleOpControl(){
        InputManager = new ControllerInputManager(this);
        InputManager.addListener(this);
        InputManager.Init();

        RobotIMU = new IMU(this);
        RobotIMU.Init();

        RobotChassisControl = new ChassisControl(this, DriveCalc, RobotIMU, BaseSpeed, BaseOffset, Motors);
        RobotChassisControl.Init();
    }

    protected void StartTeleOpControl(){
        RobotIMU.Start();
    }

    protected void LoopTeleOpControl(){
        InputManager.Loop();
        RobotChassisControl.Loop();
    }
}
