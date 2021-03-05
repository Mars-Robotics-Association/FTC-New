package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.Input.ControllerInput;
import org.firstinspires.ftc.teamcode.Core.Input.ControllerInputListener;
<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.Core.Robots.CuriosityUltimateGoalControl;
=======
//import org.firstinspires.ftc.teamcode.Core.Robots.CuriosityUltimateGoalControl;
>>>>>>> Stashed changes
import org.firstinspires.ftc.teamcode.Core.Robots.MecanumBaseControl;
import org.firstinspires.ftc.teamcode.MechanicalControl.Kenobi.WobbleGoalController;

@TeleOp(name = "Kenobi TeleOp", group = "Competition")
@Config
public class OpportunityTeleOp extends OpMode implements ControllerInputListener
{
    ////Dependencies////
    private WobbleGoalController wobble;
    private MecanumBaseControl control;
    private ControllerInput controllerInput1;
    private ControllerInput controllerInput2;
    private WobbleGoalController wobble;
    ////Variables////
    //Tweaking Vars
    public static double driveSpeed = 1;//used to change how fast robot drives
    public static double turnSpeed = 1;//used to change how fast robot turns

    private double speedMultiplier = 1;

    private boolean busy = false;
    private double turnOffset = 0;

    private int payloadController = 2;

    private double ArmMultiplier = 1;
    private boolean ArmPos = false;
    private boolean ArmNeg = false;

    @Override
    public void init() {
        wobble = new WobbleGoalController();
<<<<<<< Updated upstream
        wobble.Init(this, hardwareMap.crservo.get("wobbleCRServo"), hardwareMap.servo.get("leftServo"), hardwareMap.servo.get("rightServo"));
//this means this opmode is being passed, the hardware map statments as stated


        control = new MecanumBaseControl(this, true, true, true);
        control.InitCoreRobotModules();
=======
        wobble.Init(
                hardwareMap.crservo.get("liftservo"),
                hardwareMap.servo.get("leftarm"),
                hardwareMap.servo.get("rightarm")
        );
        control = new MecanumBaseControl(this, true, true, true);
        control.InitCoreRobotModules();

>>>>>>> Stashed changes

        controllerInput2 = new ControllerInput(gamepad2, 2);
        controllerInput1 = new ControllerInput(gamepad1, 1);
        controllerInput1.addListener(this);
        controllerInput2.addListener(this);

        telemetry.addData("Speed Multiplier", speedMultiplier);
        telemetry.update();
    }

    @Override
<<<<<<< Updated upstream
    public void start(){control.StartCoreRobotModules();}

    private final double ArmMultiplier = 2;
    private double ArmDirection = 0;
    private boolean LeftBumper = false;
    private boolean RightBumper = false;
    private double dir = 0;

=======
    public void start(){
        control.StartCoreRobotModules();
        wobble.start();
    }
>>>>>>> Stashed changes

    @Override
    public void loop() {
        controllerInput1.Loop();
        controllerInput2.Loop();

        if(!busy) {
            ManageDriving();
        }

<<<<<<< Updated upstream
        if(LeftBumper){ArmDirection++;}
        if(RightBumper){ArmDirection--;}
        wobble.SetWobbleLiftPower(ArmDirection*ArmMultiplier);
=======
        int dir = 0;

        if(ArmPos){dir++;}
        if(ArmNeg){dir--;}

        wobble.SetWobbleLiftPower(dir*ArmMultiplier);

        wobble.Loop();
>>>>>>> Stashed changes

    }

    private void ManageDriving() {
        double moveX = -gamepad1.left_stick_y*driveSpeed*speedMultiplier;
        double moveY = -gamepad1.left_stick_x*driveSpeed*speedMultiplier;
        double turn = gamepad1.right_stick_x*turnSpeed*speedMultiplier + turnOffset;
        control.GetOrion().MoveRaw(moveX, moveY, turn);
    }

    @Override
    public void APressed(double controllerNumber) {
        if(controllerNumber == 1) {
            if (speedMultiplier == 1) speedMultiplier = 0.5;
            else if (speedMultiplier == 0.5) speedMultiplier = 0.25;
            else speedMultiplier = 1;
        }
    }

    @Override
    public void BPressed(double controllerNumber) {
        if(controllerNumber == 1){
            wobble.RaiseWobbleLift();
        }
    }

    @Override
    public void XPressed(double controllerNumber) {
        if(controllerNumber == 1){
            wobble.LowerWobbleLift();
        }
    }

    @Override
    public void YPressed(double controllerNumber) {

    }

    @Override
    public void AHeld(double controllerNumber) {

    }

    @Override
    public void BHeld(double controllerNumber) {

    }

    @Override
    public void XHeld(double controllerNumber) {
    }

    @Override
    public void YHeld(double controllerNumber) {
    }

    @Override
    public void AReleased(double controllerNumber) {

    }

    @Override
    public void BReleased(double controllerNumber)  {

    }

    @Override
    public void XReleased(double controllerNumber) {
    }

    @Override
    public void YReleased(double controllerNumber) {
    }

    @Override
    public void LBPressed(double controllerNumber) {
<<<<<<< Updated upstream
        if(controllerNumber == 1) {
            LeftBumper = true;
        }
=======
        if(controllerNumber == 1) {ArmPos = true;}
>>>>>>> Stashed changes
    }

    @Override
    public void RBPressed(double controllerNumber) {
<<<<<<< Updated upstream
        if(controllerNumber == 1) {
            RightBumper = true;
        }
=======
        if(controllerNumber == 1) {ArmPos = true;}
>>>>>>> Stashed changes
    }

    @Override
    public void LTPressed(double controllerNumber) {

    }

    @Override
    public void RTPressed(double controllerNumber) {

    }

    @Override
    public void LBHeld(double controllerNumber) {

    }

    @Override
    public void RBHeld(double controllerNumber) {
    }

    @Override
    public void LTHeld(double controllerNumber) {

    }

    @Override
    public void RTHeld(double controllerNumber) {

    }

    @Override
    public void LBReleased(double controllerNumber) {
<<<<<<< Updated upstream
        if(controllerNumber == 1) {
            LeftBumper = false;
        }
=======
        if(controllerNumber == 1) {ArmPos = false;}
>>>>>>> Stashed changes
    }

    @Override
    public void RBReleased(double controllerNumber) {
<<<<<<< Updated upstream
        if(controllerNumber == 1) {
            RightBumper = false;
        }
=======
        if(controllerNumber == 1) {ArmNeg = false;}
>>>>>>> Stashed changes
    }

    @Override
    public void LTReleased(double controllerNumber) {

    }

    @Override
    public void RTReleased(double controllerNumber) {

    }
}
