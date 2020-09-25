package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemobotAutoFuncs;
import org.firstinspires.ftc.teamcode.MechanicalControl.DemobotChassis;
import org.firstinspires.ftc.teamcode.MechanicalControl.Shooter;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class DemobotControl
{
    ////Dependencies////
    //Mechanical Components
    private DemobotChassis chassis;
    private Shooter robotShooter;
    private Intake robotIntake;
    //Core
    private PID pid; //Look here: https://github.com/tekdemo/MiniPID-Java for how to use it
    private IMU imu;
    //Autonomous Functions
    private DemobotAutoFuncs autoFuncs;
    //Orion Navigator
    private OrionNavigator orion;
    //OpMode
    private OpMode currentOpMode;
    //Drive Motors
    private DcMotor FR;
    private DcMotor FL;
    private DcMotor RR;
    private DcMotor RL;
    //Shooter/Intake Motors
    private DcMotor intakeMotor;
    private DcMotor spinnerMotor;
    private DcMotor loaderMotor;
    private ServoController shooterAimer;

    ////Variables////
    //Calibration
    private double shooterHeight = 0.5; //in meters
    //Util
    private double gyroOffset;

    //TODO: ===CHANGE THESE TO CONTROL WHAT YOU ARE RUNNING===
    private boolean USE_CHASSIS = true;
    private boolean USE_PAYLOAD = false;
    private boolean USE_NAVIGATOR = false;
    public boolean isUSE_CHASSIS(){return USE_CHASSIS;}
    public boolean isUSE_PAYLOAD(){return USE_PAYLOAD;}
    public boolean isUSE_NAVIGATOR(){return USE_NAVIGATOR;}


    //Initializer
    public DemobotControl(OpMode setOpMode, boolean useChassis, boolean usePayload, boolean useNavigator)
    {
        currentOpMode = setOpMode;
        USE_CHASSIS = useChassis;
        USE_PAYLOAD = usePayload;
        USE_NAVIGATOR = useNavigator;
    }

    //SETUP METHODS//
    public void Init(){
        //TODO: ==INIT CORE MODULES==
        imu = new IMU(currentOpMode);
        pid = new PID(0,0,0);

        if(USE_NAVIGATOR) {
            //TODO: ===INIT ORION===
            orion = new OrionNavigator(currentOpMode, this);
            orion.Init();

            //TODO: ===INIT AUTO FUNCS===
            autoFuncs = new DemobotAutoFuncs(this);
        }

        //TODO: ===INIT MOTORS- Change strings to what you want===
        if(USE_CHASSIS) {
            FR = currentOpMode.hardwareMap.dcMotor.get("FR");
            FL = currentOpMode.hardwareMap.dcMotor.get("FL");
            RR = currentOpMode.hardwareMap.dcMotor.get("RR");
            RL = currentOpMode.hardwareMap.dcMotor.get("RL");
        }
        if(USE_PAYLOAD) {
            intakeMotor = currentOpMode.hardwareMap.dcMotor.get("IM");
            spinnerMotor = currentOpMode.hardwareMap.dcMotor.get("SM");
            loaderMotor = currentOpMode.hardwareMap.dcMotor.get("LM");
            shooterAimer = currentOpMode.hardwareMap.crservo.get("SA").getController();
        }

        //TODO: ===INIT CHASSIS AND/OR PAYLOAD===
        if(USE_CHASSIS) {
            chassis = new DemobotChassis(imu, FR, FL, RR, RL, currentOpMode.telemetry);//Create chassis instance w/ motors
            chassis.Init();
        }
        if(USE_PAYLOAD) {
            robotShooter = new Shooter();
            robotShooter.Init(spinnerMotor, loaderMotor, shooterAimer, 20);

            robotIntake = new Intake();
            robotIntake.Init(intakeMotor);
        }
    }

    public void Start(){
        imu.Start();
        imu.ResetGyro();
    }

    //CALLABLE METHODS//
    public void RawDrive(double angle, double speed, double turnOffset) {
        //Used in continuously in teleop to move robot at any angle using imu and pid controller
        //Enter angle to move, speed, and a turn offset for turning while moving
        chassis.MoveAtAngle(angle, speed, turnOffset);
    }
    public void RawTurn(double speed){
        //Used continuously in teleop to turn the robot
        //Enter speed for turn- positive speed turns left, negative right
        chassis.SpotTurn(speed);
    }
    public void Brake(){
        //Called once to brake the robot
        chassis.Brake();
    }
    public void OdometryDrive(double angle, double speed, double distance) {
        //Used to autonomously drive a certain distance at a certain angle.
        //Enter angle, speed, and distance
        autoFuncs.MoveSpline(angle, distance, speed);
    }
    public void SpotTurn(double angle, double speed) {
        //Turns the robot on center of the wheel axis using a ramp turn
        //Enter target angle and turn speed
        autoFuncs.SpotTurn(speed, angle, 0);
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
        //double[] vumarkOffset = GetOrion().GetVuforia(0);
        robotShooter.SetTrajectory(10, 5, 0);
        //TODO: apply correct offset to angle
        SpotTurn(robotShooter.GetTargetHeading(), 0.5);
        robotShooter.Aim();
    }
    public void SpinUpShooter(){
        //Spins the shooter up
        robotShooter.SpinUp();
    }
    public void FireShooter(){
        //Fires shooter
        robotShooter.Fire();
    }
    public void Intake() {
        //Runs the intake of the robot
        robotIntake.IntakeOn();
    }

    //GETTER METHODS//
    public double GetRobotAngle(){
        //Returns the gyro with an offset applied
        return imu.GetRawAngles().firstAngle - gyroOffset;
    }
    //Dependency Getters
    public OrionNavigator GetOrion(){return orion;}
    public IMU GetImu(){return imu;}
    public DemobotChassis GetChassis(){return chassis;}
    public org.firstinspires.ftc.teamcode.MechanicalControl.Shooter GetShooter(){return robotShooter;}
    public DemobotAutoFuncs GetAutoFuncs(){return autoFuncs;}
    public PID GetPID(){return chassis.GetHeadingPID();}
    public OpMode GetOpMode(){return currentOpMode;}

    //SETTER METHODS//
    public void SetDrivePID(double p, double i, double d){
        chassis.SetPIDCoefficients(p,i,d);
    }

    //PRIVATE METHODS//
    private void OffsetGyro(){
        //Offsets the gryo so the current heading can be zero with GetRobotAngle()
        gyroOffset = imu.GetRawAngles().firstAngle;
    }
}
