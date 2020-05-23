package org.firstinspires.ftc.teamcode.Archive.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Archive.Algorithm.DriveAlgorithm;
import org.firstinspires.ftc.teamcode.Archive.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Archive.Control.EncoderAutonomousControl;
import org.firstinspires.ftc.teamcode.Archive.Sensor.IMU;

public abstract class AutonomousOpMode extends OpMode
{
    //REFERENCES
    //Default Refs
    protected DriveAlgorithm DriveCalc;
    protected DcMotor[] Motors;

    //Other
    protected AutonomousControl AutonomousControl;
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
    public void SetAutonomousStats(DriveAlgorithm setDriveCalc, double setBaseSpeed, double setBaseOffset, DcMotor[] setMotors) {
        DriveCalc = setDriveCalc;
        BaseSpeed = setBaseSpeed;
        BaseOffset = setBaseOffset;
        Motors = setMotors;
    }

    protected void InitAutonomousControl(){

        RobotIMU = new IMU(this);
        RobotIMU.Init();

        AutonomousControl = new EncoderAutonomousControl(this, Motors);
        AutonomousControl.Init();
    }

    protected void StartAutonomousControl(){
        RobotIMU.Start();
    }

    protected void LoopAutonomousControl(){
        AutonomousControl.Loop();
    }
}
