package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemobotAutoFuncs;
import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Shooter;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class DemobotControl extends RobotControl
{
    ////Dependencies////
    //Mechanical Components
    private Shooter robotShooter;
    private Intake robotIntake;
    //Autonomous Functions
    protected DemobotAutoFuncs autoFuncs;
    //Shooter/Intake Motors
    private DcMotor intakeMotor;
    private DcMotor spinnerMotor;
    private DcMotor loaderMotor;
    private ServoController shooterAimer;

    ////Variables////
    //Calibration
    private double shooterHeight = 0.5; //in meters

    public DemobotControl(OpMode setOpMode, boolean useChassis, boolean usePayload, boolean useNavigator) {
        super(setOpMode, useChassis, usePayload, useNavigator);
    }

    //SETUP METHODS//
    public void Init(){
        //TODO ===INIT PAYLOAD===
        if(USE_PAYLOAD) {
            intakeMotor = currentOpMode.hardwareMap.dcMotor.get("IM");
            spinnerMotor = currentOpMode.hardwareMap.dcMotor.get("SM");
            loaderMotor = currentOpMode.hardwareMap.dcMotor.get("LM");
            shooterAimer = currentOpMode.hardwareMap.crservo.get("SA").getController();

            robotShooter = new Shooter();
            robotShooter.Init(spinnerMotor, loaderMotor, shooterAimer, 20);

            robotIntake = new Intake();
            robotIntake.Init(new DcMotor[]{intakeMotor}, new double[]{1});
        }

        //TODO ===INIT CORE ROBOT===
        super.InitCoreRobotModules();

        if(USE_NAVIGATOR) {
            //TODO: ===INIT AUTO FUNCS===
            autoFuncs = new DemobotAutoFuncs(this);
        }
    }

    public void Start(){
        super.StartCoreRobotModules();
    }

    //CALLABLE METHODS//
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
        robotIntake.SetIntakePower(1);
    }

    public DemobotAutoFuncs GetAutoFuncs(){return autoFuncs;}

}
