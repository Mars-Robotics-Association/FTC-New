package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemobotAutoFuncs;
import org.firstinspires.ftc.teamcode.MechanicalControl.DemobotChassis;
import org.firstinspires.ftc.teamcode.Navigation.Navigation.Odometry.DemoBotOdometry;
import org.firstinspires.ftc.teamcode.Navigation.Navigation.Vuforia.DemobotTargetFinder;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class DemobotControl
{
    ////Dependencies////
    private DemoBotOdometry Odometry;
    private IMU Imu;
    private DemobotChassis Chassis;
    private org.firstinspires.ftc.teamcode.MechanicalControl.Shooter RobotShooter;
    private DemobotAutoFuncs AutoFuncs;
    private DemobotTargetFinder VuforiaTargetFinder;
    private PID Pid; //Look here: https://github.com/tekdemo/MiniPID-Java for how to use it
    private OpMode CurrentOpMode;
    private Intake RobotIntake;
    //Drive Motors
    private DcMotor FR;
    private DcMotor FL;
    private DcMotor RR;
    private DcMotor RL;
    //Shooter/Intake Motors
    private DcMotor IntakeMotor;
    private DcMotor SpinnerMotor;
    private DcMotor LoaderMotor;
    private ServoController ShooterAimer;

    ////Variables////
    //Calibration
    private double ShooterHeight = 0.5; //in meters
    //Util
    private double GyroOffset;


    //Initializer
    public DemobotControl(OpMode setOpMode)
    {
        CurrentOpMode = setOpMode;
    }

    //SETUP METHODS//
    public void Init(){
        //Init motors- change string to name of motors in config
        FR = CurrentOpMode.hardwareMap.dcMotor.get("FR");
        FL = CurrentOpMode.hardwareMap.dcMotor.get("FL");
        RR = CurrentOpMode.hardwareMap.dcMotor.get("RR");
        RL = CurrentOpMode.hardwareMap.dcMotor.get("RL");
        //IntakeMotor = CurrentOpMode.hardwareMap.dcMotor.get("IM");
        /*SpinnerMotor = CurrentOpMode.hardwareMap.dcMotor.get("SM");
        LoaderMotor = CurrentOpMode.hardwareMap.dcMotor.get("LM");
        ShooterAimer = CurrentOpMode.hardwareMap.crservo.get("SA").getController();*/

        /*Odometry = new DemoBotOdometry(RL, RR);
        Odometry.Reset();*/

        Imu = new IMU(CurrentOpMode);
        Pid = new PID(0,0,0);//Create the pid controller. Specify (p,i,d) constants

        Chassis = new DemobotChassis(Imu, FR, FL, RR, RL, CurrentOpMode.telemetry);//Create chassis instance w/ motors
        Chassis.Init();

        /*RobotShooter = new Shooter();
        RobotShooter.Init(SpinnerMotor, LoaderMotor, ShooterAimer, 20);*/

        /*RobotIntake = new Intake();
        RobotIntake.Init(IntakeMotor);*/

        //AutoFuncs = new DemobotAutoFuncs(this);
        //VuforiaTargetFinder = new DemobotTargetFinder();
    }

    public void Start(){
        Imu.Start();
        Imu.ResetGyro();
    }

    //CALLABLE METHODS//
    public void RawDrive(double angle, double speed, double turnOffset) {
        //Used in continuously in teleop to move robot at any angle using imu and pid controller
        //Enter angle to move, speed, and a turn offset for turning while moving
        Chassis.MoveAtAngle(angle, speed, turnOffset);
    }
    public void RawTurn(double speed){
        //Used continuously in teleop to turn the robot
        //Enter speed for turn- positive speed turns left, negative right
        Chassis.SpotTurn(speed);
    }
    public void Brake(){
        //Called once to brake the robot
        Chassis.Brake();
    }
    public void OdometryDrive(double angle, double speed, double distance) {
        //Used to autonomously drive a certain distance at a certain angle.
        //Enter angle, speed, and distance
        AutoFuncs.MoveAtAngle(angle, distance, speed);
    }
    public void SpotTurn(double angle, double speed) {
        //Turns the robot on center of the wheel axis using a ramp turn
        //Enter target angle and turn speed
        AutoFuncs.SpotTurn(speed, angle, 0);
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
        //TODO: get distance, x, y, and heading from vuforia
        RobotShooter.SetTrajectory(10, 5, 0);
        //TODO: apply correct offset to angle
        SpotTurn(RobotShooter.GetTargetHeading(), 0.5);
        RobotShooter.Aim();
    }
    public void SpinUpShooter(){
        //Spins the shooter up
        RobotShooter.SpinUp();
    }
    public void FireShooter(){
        //Fires shooter
        RobotShooter.Fire();
    }
    public void Intake() {
        //Runs the intake of the robot
        RobotIntake.IntakeOn();
    }

    //GETTER METHODS//
    public double GetRobotAngle(){
        //Returns the gyro with an offset applied
        return Imu.GetRawAngles().firstAngle - GyroOffset;
    }
    //Dependency Getters
    public DemoBotOdometry GetOdometry(){return Odometry;}
    public IMU GetImu(){return Imu;}
    public DemobotChassis GetChassis(){return Chassis;}
    public org.firstinspires.ftc.teamcode.MechanicalControl.Shooter GetShooter(){return RobotShooter;}
    public DemobotAutoFuncs GetAutoFuncs(){return AutoFuncs;}
    public DemobotTargetFinder GetVuforiaTargetFinder(){return VuforiaTargetFinder;}
    public PID GetPID(){return Chassis.GetHeadingPID();}
    public OpMode GetOpMode(){return CurrentOpMode;}

    //SETTER METHODS//
    public void SetDrivePID(double p, double i, double d){
        Chassis.SetPIDCoefficients(p,i,d);
    }

    //PRIVATE METHODS//
    private void OffsetGyro(){
        //Offsets the gryo so the current heading can be zero with GetRobotAngle()
        GyroOffset = Imu.GetRawAngles().firstAngle;
    }
}
