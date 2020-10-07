package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousFunctions.SchrodingerAutoFuncs;
import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerArm;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerFoundationGrabbers;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerGripper;

//The class used to control schrodinger. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the schrodinger.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class SchrodingerControl extends RobotControl
{
    ////Dependencies////
    //Mechanical Components
    private SchrodingerArm arm;
    private SchrodingerGripper gripper;
    private Intake intake;
    private SchrodingerFoundationGrabbers grabbers;

    //Auto Funcs
    SchrodingerAutoFuncs autoFuncs;

    ////Variables////
    //Calibration
    private double armExtension = 0.5; //in meters

    public SchrodingerControl(OpMode setOpMode, boolean useChassis, boolean usePayload, boolean useNavigator) {
        super(setOpMode, useChassis, usePayload, useNavigator);
    }

    //SETUP METHODS//
    public void Init(){
        //TODO ===INIT PAYLOAD===
        if(USE_PAYLOAD) {
            Servo gripperR = currentOpMode.hardwareMap.servo.get("gr");
            Servo gripperL = currentOpMode.hardwareMap.servo.get("gl");
            Servo headingServo = currentOpMode.hardwareMap.servo.get("gh");
            Servo rotationServoR = currentOpMode.hardwareMap.servo.get("rr");
            Servo rotationServoL = currentOpMode.hardwareMap.servo.get("rl");

            Servo armServoR = currentOpMode.hardwareMap.servo.get("ar");
            Servo armServoL = currentOpMode.hardwareMap.servo.get("al");
            DcMotor armMotor = currentOpMode.hardwareMap.dcMotor.get("AM");

            Servo grabberServoR = currentOpMode.hardwareMap.servo.get("fr");
            Servo grabberServoL = currentOpMode.hardwareMap.servo.get("fl");

            DcMotor intakeMotorR = currentOpMode.hardwareMap.dcMotor.get("IR");
            DcMotor intakeMotorL = currentOpMode.hardwareMap.dcMotor.get("IL");

            arm = new SchrodingerArm(armMotor, armServoR, armServoL);
            gripper = new SchrodingerGripper(gripperR, gripperL, headingServo, rotationServoR, rotationServoL);
            grabbers = new SchrodingerFoundationGrabbers(grabberServoR, grabberServoL);

            intake = new Intake();
            intake.Init(new DcMotor[]{intakeMotorR, intakeMotorL}, new double[]{1,-1});
        }

        //TODO ===INIT CORE ROBOT===
        super.InitCoreRobotModules();

        if(USE_NAVIGATOR) {
            //TODO: ===INIT AUTO FUNCS===
            autoFuncs = new SchrodingerAutoFuncs(arm, gripper, intake);
        }
    }

    public void Start(){
        super.StartCoreRobotModules();
    }

    //CALLABLE METHODS//
    public void ChangeArmRotation(double speed){arm.ChangeRotation(speed);}
    public void ChangeArmExtension(double speed){arm.ChangeExtension(speed);}
    public void ArmToIntake(){
        gripper.SetGripperState(true);
        gripper.SetTargetRotation(180);
        arm.ResetArm();
    }
    public void ArmToPlace(int stackHeight){
        gripper.SetTargetRotation(0);
        gripper.SetGripperState(true);
        arm.SetTargetRotation(-90);
    }
    public void SetGripperState(boolean closed){gripper.SetGripperState(closed);}
    public void SwitchGripperState(){gripper.SwitchGripperState();}
    public void SetFoundationGrabberState(boolean down){grabbers.SetGrabberState(down);}
    public void SwitchFoundationGrabberState(){grabbers.SwitchGrabberState();}
    public void Dance(){autoFuncs.Dance();}
    public void Intake(double power) {
        //Runs the intake of the robot
        intake.SetIntakePower(power);
    }
    public void PrintTelemetry(){
        gripper.PrintTelemetry(currentOpMode.telemetry);
        grabbers.PrintTelemetry(currentOpMode.telemetry);
        arm.PrintTelemetry(currentOpMode.telemetry);
    }

    //PUBLIC GETTERS
    public SchrodingerGripper GetGripper(){return gripper;}
    public SchrodingerArm GetArm(){return arm;}
    public Intake GetIntake(){return intake;}
}
