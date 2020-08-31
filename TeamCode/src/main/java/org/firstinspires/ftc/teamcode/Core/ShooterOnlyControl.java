package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemobotAutoFuncs;
import org.firstinspires.ftc.teamcode.Mechanical.DemobotChassis;
import org.firstinspires.ftc.teamcode.Mechanical.Intake;
import org.firstinspires.ftc.teamcode.Mechanical.Shooter;
import org.firstinspires.ftc.teamcode.Odometry.DemoBotOdometry;
import org.firstinspires.ftc.teamcode.Vuforia.DemobotTargetFinder;
import org.firstinspires.ftc.teamcode.Vuforia.VuMarkNavigation;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Shooter

public class ShooterOnlyControl
{
    ////Dependencies////
    private IMU Imu;
    private Shooter RobotShooter;
    private DemobotAutoFuncs AutoFuncs;
    private DemobotTargetFinder VuforiaTargetFinder;
    private PID Pid; //Look here: https://github.com/tekdemo/MiniPID-Java for how to use it
    private OpMode CurrentOpMode;
    private Intake RobotIntake;
    private VuMarkNavigation vuforia;
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
    public ShooterOnlyControl(OpMode setOpMode)
    {
        CurrentOpMode = setOpMode;
    }

    //SETUP METHODS//
    public void Init(){
        //Init motors- change string to name of motors in config
        //IntakeMotor = CurrentOpMode.hardwareMap.dcMotor.get("IM");
        SpinnerMotor = CurrentOpMode.hardwareMap.dcMotor.get("SM");
        LoaderMotor = CurrentOpMode.hardwareMap.dcMotor.get("LM");
        ShooterAimer = CurrentOpMode.hardwareMap.crservo.get("SA").getController();

        Imu = new IMU(CurrentOpMode);
        Pid = new PID(0,0,0);//Create the pid controller. Specify (p,i,d) constants

        RobotShooter = new Shooter();
        RobotShooter.Init(SpinnerMotor, LoaderMotor, ShooterAimer, 20);

        /*RobotIntake = new Intake();
        RobotIntake.Init(IntakeMotor);*/

        VuforiaTargetFinder = new DemobotTargetFinder();
        vuforia = new VuMarkNavigation();
    }

    public void Start(){
        Imu.Start();
        Imu.ResetGyro();
    }

    public void ShooterGoBoom(double distanceFromTarget, double heightFromTarget) {
        //Shoots the ball at target with either linear or arc shooter
        //Enter distance and height from target
    }
    public void AimShooter() {
        //Aims the shooter at the specified target
        //Enter vumark to look for

        //TODO: get distance, x, y, and heading from vuforia
        double[] data = vuforia.GetData();
        double tX = data[0];
        double tY = data[1];
        double tZ = data[2];
        double dist = data[3];
        double rZ = data[4];
        if(data[3] == 0.0 && data[2] == 0.0 && data[1] == 0.0 && data[0] == 0.0 && data[4] == 0.0) {
            RobotShooter.SetTrajectory(10, 5, 0);
        }else{
            RobotShooter.SetTrajectory(dist,tX,rZ);
        }
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
    public IMU GetImu(){return Imu;}
    public Shooter GetShooter(){return RobotShooter;}
    public DemobotAutoFuncs GetAutoFuncs(){return AutoFuncs;}
    public DemobotTargetFinder GetVuforiaTargetFinder(){return VuforiaTargetFinder;}
    public PID GetPID(){return Pid;}
    public OpMode GetOpMode(){return CurrentOpMode;}

    //SETTER METHODS//

    //PRIVATE METHODS//
    private void OffsetGyro(){
        //Offsets the gryo so the current heading can be zero with GetRobotAngle()
        GyroOffset = Imu.GetRawAngles().firstAngle;
    }
}
