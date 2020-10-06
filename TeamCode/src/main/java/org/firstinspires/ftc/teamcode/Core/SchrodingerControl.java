package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.SchrodingerArm;
import org.firstinspires.ftc.teamcode.MechanicalControl.Shooter;

//The class used to control schrodinger. Autonomous functions, opmodes, and other scripts can call
//methods in here to control the schrodinger.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

public class SchrodingerControl extends RobotControl
{
    ////Dependencies////
    //Mechanical Components
    private SchrodingerArm robotArm;
    private Intake robotIntake;
    //Shooter/Intake Motors
    private DcMotor intakeMotor;
    private DcMotor spinnerMotor;
    private DcMotor loaderMotor;
    private ServoController shooterAimer;

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
            intakeMotor = currentOpMode.hardwareMap.dcMotor.get("IM");
            spinnerMotor = currentOpMode.hardwareMap.dcMotor.get("SM");
            loaderMotor = currentOpMode.hardwareMap.dcMotor.get("LM");
            shooterAimer = currentOpMode.hardwareMap.crservo.get("SA").getController();

            robotArm = new SchrodingerArm();

            robotIntake = new Intake();
            robotIntake.Init(new DcMotor[]{intakeMotor});
        }

        //TODO ===INIT CORE ROBOT===
        super.InitCoreRobotModules();
    }

    public void Start(){
        super.StartCoreRobotModules();
    }

    //CALLABLE METHODS//
    public void ChangeArmRotation(double amount){}
    public void ArmToIntake(){}
    public void ArmToPlace(int stackHeight){}
    public void SetGripperState(boolean closed){}
    public void SetFoundationGrabberState(boolean down){}
    public void Dance(){}
    public void Intake() {
        //Runs the intake of the robot
        robotIntake.SetIntakePower(1);
    }
}
