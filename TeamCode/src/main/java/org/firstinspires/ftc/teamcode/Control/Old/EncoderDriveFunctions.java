package org.firstinspires.ftc.teamcode.Control.Old;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EncoderDriveFunctions extends AutonomousFunction
{
    //REFERENCES
    private EncoderDriveLogic DriveLogic;

    private DcMotor FR;
    private DcMotor FL;
    private DcMotor RR;
    private DcMotor RL;


    //VARIABLES
    private double targetDistY = 0;
    private double targetDistX = 0;
    private double currentSpeed = 0;

    private double rotationsPerCmY = 0;//TODO get actual values so autonomous moves
    private double rotationsPerCmX = 0;

    //Initializer
    public EncoderDriveFunctions(DcMotor setFR, DcMotor setFL, DcMotor setRR, DcMotor setRL) {
        FR = setFR;
        FL = setFL;
        RR = setRR;
        RL = setRL;
    }

    @Override
    public void Init() {
        DriveLogic = new EncoderDriveLogic(FR, FL, RR, RL);
        DriveLogic.Init();
    }

    @Override
    public void Start() {
        DriveLogic.Start();
    }

    @Override
    public void Loop() {
        DriveLogic.Loop();
    }

    @Override
    public void End() {
        DriveLogic.End();
    }

    @Override
    public void GoForwards(double distance, double speed) {
        targetDistY = distance * rotationsPerCmY;
        currentSpeed = speed;
    }

    @Override
    public void GoSideways(double distance, double speed) {
        targetDistY = distance * rotationsPerCmY;
        currentSpeed = speed;
    }

    @Override
    public void GoAtAngle(double angle, double distance, double speed) {

    }

    @Override
    public void GoAtDeltaVector(double moveAngle, double xdist, double ydist, double speed) {

    }

    @Override
    public void StopMoving() {
        currentSpeed = 0;
    }
}
