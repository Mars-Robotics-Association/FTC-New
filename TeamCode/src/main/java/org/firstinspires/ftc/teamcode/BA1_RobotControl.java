package org.firstinspires.ftc.teamcode;

import android.provider.ContactsContract;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class BA1_RobotControl extends B_Control
{
    //References
    OpMode opMode;
    CB_DriveAlgorithm DriveCalc;
    EB1_IMU IMU;

    //Variables
    private double BaseOffset = 0;
    private double HeadlessOffset = 0;
    private double MoveAngle = 0;
    private boolean IsHeadless = true;
    private double BaseSpeed = 1;
    private double CurrentSpeed = 0;
    private double TurnOffset = 0;

    public BA1_RobotControl(OpMode setOpMode, CB_DriveAlgorithm setDriveCalc, EB1_IMU setIMU, double setBaseSpeed, double setBaseOffset) {
        opMode = setOpMode;
        DriveCalc = setDriveCalc;
        IMU = setIMU;
        BaseSpeed = setBaseSpeed;
        BaseOffset = setBaseOffset;
    }

    @Override
    public void Init(){

    }

    @Override
    public void Loop() {
        double directMoveAngle = MoveAngle + BaseOffset;
        if(IsHeadless){directMoveAngle += (IMU.angles.firstAngle - HeadlessOffset);}
        DriveCalc.CalculateWheelSpeeds(directMoveAngle, CurrentSpeed, 0);
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
        HeadlessOffset = IMU.GetAngles().firstAngle;
    }
}
