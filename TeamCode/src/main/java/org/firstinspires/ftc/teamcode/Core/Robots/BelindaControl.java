package org.firstinspires.ftc.teamcode.Core.Robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.MechanicalControl.Belinda.SquirtleShooterController;
import org.firstinspires.ftc.teamcode.MechanicalControl.MotorizedIntake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Demobot.DemobotShooter;

//The class used to control the demobot. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class BelindaControl extends MecanumBaseControl
{
    ////Dependencies////
    //Mechanical Components
    private SquirtleShooterController shooterIntake;
    //Shooter/Intake Motors
    private DcMotor shooterMotor1;
    private DcMotor shooterMotor2;
    private Servo intakeServo;
    private Servo loaderServo;

    ////Variables////
    //Calibration
    private double shooterHeight = 0.5; //in meters

    public BelindaControl(OpMode setOpMode, boolean useChassis, boolean usePayload, boolean useNavigator) {
        super(setOpMode, useChassis, usePayload, useNavigator);
    }

    //SETUP METHODS//
    public void Init(){
        //TODO ===INIT PAYLOAD===
        if(USE_PAYLOAD) {
            shooterMotor1 = currentOpMode.hardwareMap.dcMotor.get("SM1");
            shooterMotor2 = currentOpMode.hardwareMap.dcMotor.get("SM2");
            intakeServo = currentOpMode.hardwareMap.servo.get("intakeServo");
            loaderServo = currentOpMode.hardwareMap.servo.get("loaderServo");

            shooterIntake = new SquirtleShooterController();
            shooterIntake.Init(new DcMotor[]{shooterMotor1, shooterMotor2}, new double[]{1,1}, new Servo[]{intakeServo, loaderServo}, 1);
        }

        //TODO ===INIT CORE ROBOT===
        super.InitCoreRobotModules();

        if(USE_NAVIGATOR) {
        }
    }

    public void Start(){
        super.StartCoreRobotModules();
    }

    //CALLABLE METHODS//
    public void ShooterOn(){
        //Fires shooter
        shooterIntake.ShooterOn();
    }
    public void ShooterOff(){
        //Stops shooter
        shooterIntake.ShooterOff();
    }
    public void Intake() {
        //Runs the intake of the robot
        shooterIntake.IntakeOn();
    }
    public void StopIntake() {
        //Stops the intake of the robot
        shooterIntake.IntakeOff();
    }
}
