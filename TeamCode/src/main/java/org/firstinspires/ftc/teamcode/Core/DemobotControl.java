package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanical.DemoArcShooter;
import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemobotAutoFuncs;
import org.firstinspires.ftc.teamcode.Mechanical.DemobotChassis;
import org.firstinspires.ftc.teamcode.Odometry.DemoBotOdometry;
import org.firstinspires.ftc.teamcode.Vuforia.DemobotTargetFinder;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

public class DemobotControl
{
    //Dependencies
    private DemoBotOdometry Odometry;
    private IMU Imu;
    private DemobotChassis Chassis;
    private DemoArcShooter Shooter;
    private DemobotAutoFuncs AutoFuncs;
    private DemobotTargetFinder VuforiaTargetFinder;
    private PID Pid; //Look here: https://github.com/tekdemo/MiniPID-Java for how to use it
    private OpMode CurrentOpMode;

    //Variables
    private double GyroOffset;


    //Initializer
    public DemobotControl(OpMode setOpMode)
    {
        CurrentOpMode = setOpMode;
    }

    //SETUP METHODS//
    public void Init(){
        Odometry = new DemoBotOdometry();
        Imu = new IMU(CurrentOpMode);
        Chassis = new DemobotChassis();
        Shooter = new DemoArcShooter();
        AutoFuncs = new DemobotAutoFuncs(this);
        VuforiaTargetFinder = new DemobotTargetFinder();
        Pid = new PID(0,0,0);//Create the pid controller. Specify (p,i,d) constants
    }

    public void Start(){
        Imu.Start();
    }

    //CALLABLE METHODS//
    public void RawDrive(double angle, double speed, double turnOffset) {
        //Used in teleop to move robot at any angle using imu and pid controller
        //Enter angle to move, speed, and a turn offset for turning while moving
    }
    public void OdometryDrive(double angle, double speed, double distance) {
        //Used to autonomously drive a certain distance at a certain angle.
        //Enter angle, speed, and distance
    }
    public void SpotTurn(double angle, double speed) {
        //Turns the robot on center of the wheel axis using a ramp turn
        //Enter target angle and turn speed
    }
    public void SweepTurn(double angle, double speed, double turnOffset) {
        //Turns the robot gradually
        //Enter target angle, speed, and turn offset
    }
    public void ShooterGoBoom(double distanceFromTarget, double heightFromTarget) {
        //Shoots the ball at target with either linear or arc shooter
        //Enter distance and height from target
    }
    public void AimShooter() {
        //Aims the shooter at the specified target
        //Enter vumark to look for
    }
    public void Intake() {
        //Runs the intake of the robot
    }

    //GETTER METHODS//
    public double GetRobotAngle(){
        //Returns the gyro with an offset applied
        return Imu.GetAngles().firstAngle - GyroOffset;
    }
    //Dependency Getters
    public DemoBotOdometry GetOdometry(){return Odometry;}
    public IMU GetImu(){return Imu;}
    public DemobotChassis GetChassis(){return Chassis;}
    public DemoArcShooter GetShooter(){return Shooter;}
    public DemobotAutoFuncs GetAutoFuncs(){return AutoFuncs;}
    public DemobotTargetFinder GetVuforiaTargetFinder(){return VuforiaTargetFinder;}
    public PID GetPID(){return Pid;}

    //PRIVATE METHODS//
    private void OffsetGyro(){
        //Offsets the gryo so the current heading can be zero with GetRobotAngle()
        GyroOffset = Imu.GetAngles().firstAngle;
    }
}
