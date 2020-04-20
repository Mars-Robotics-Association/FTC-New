package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Algorithm.DriveAlgorithm;
import org.firstinspires.ftc.teamcode.Sensor.IMU;

public class RobotControl extends Control
{
    //References
    OpMode opMode;
    DriveAlgorithm DriveCalc;
    IMU IMURef;

    //Variables
    private double BaseOffset = 0;
    private double HeadlessOffset = 0;
    private double MoveAngle = 0;
    private boolean IsHeadless = true;

    private double BaseSpeed = 1;
    private double CurrentSpeed = 0;

    private double TurnOffset = 0;

    private DcMotor[] Motors;
    private double[] MotorSpeeds;


    public RobotControl(OpMode setOpMode, DriveAlgorithm setDriveCalc, IMU setIMU, double setBaseSpeed, double setBaseOffset, DcMotor[] setMotors) {
        opMode = setOpMode;
        DriveCalc = setDriveCalc;
        IMURef = setIMU;
        BaseSpeed = setBaseSpeed;
        BaseOffset = setBaseOffset;
        Motors = setMotors;
    }

    @Override
    public void Init(){

    }

    @Override
    public void Start() {

    }


    @Override
    public void Loop() {
        double directMoveAngle = MoveAngle + BaseOffset;
        if(IsHeadless){directMoveAngle += (IMURef.GetAngles().firstAngle - HeadlessOffset);}
        MotorSpeeds = DriveCalc.CalculateWheelSpeeds(directMoveAngle, CurrentSpeed, 0);

        int i = 0;
        for (DcMotor motor:Motors) {
            motor.setPower(MotorSpeeds[i]);
            i++;
        }
    }

    public void SetMoveAngle(double angle, boolean headless) {
        MoveAngle = angle;
        IsHeadless = headless;
    }

    public void SetSpeed(double speed){
        CurrentSpeed = speed*BaseSpeed;
    }

    public void SetTurnOffset(double offset)
    {
        TurnOffset = offset;
    }

    public void ResetHeadlessOffset() {
        HeadlessOffset = IMURef.GetAngles().firstAngle;
    }
}
