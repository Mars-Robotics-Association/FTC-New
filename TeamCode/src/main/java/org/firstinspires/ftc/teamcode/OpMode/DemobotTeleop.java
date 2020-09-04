package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.ControllerInput;
import org.firstinspires.ftc.teamcode.Core.ControllerInputListener;
import org.firstinspires.ftc.teamcode.Core.DemobotControl;

//The class for controlling the robot in teleop. Includes basic drive movement, shooter operations,
//and advanced autonomous functions.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit
//REQUIRED TO FUNCTION: Controllers

@Config
@TeleOp(name = "Demobot TeleOp")
public class DemobotTeleop extends OpMode implements ControllerInputListener
{
    ////Dependencies////
    private DemobotControl Control;
    private ControllerInput CInput;
    FtcDashboard dashboard;

    ////Variables////
    //Tweaking Vars
    public static double TurnWhileDrivingSpeed = 1;//used to change how fast robot turns when driving
    public static double DriveSpeed = 1;//used to change how fast robot drives
    public static double TurnSpeed = 1;//used to change how fast robot turns
    public static double p = 0;
    public static double i = 0;
    public static double d = 0;
    //Utility Vars
    private boolean Busy = false;


    @Override
    public void init() {
        //Sets up demobot control class
        Control = new DemobotControl(this);
        Control.Init();

        //Sets up controller input
        CInput = new ControllerInput(gamepad1);
        CInput.Init();
        CInput.addListener(this);

        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
    }

    @Override
    public void start(){
        Control.Start();
    }

    @Override
    public void loop() {
        //Only run if robot isn't busy
        if(!Busy) {
            MangeDriveMovement();
            //ManageShooterIntake();

        }

        Control.SetDrivePID(p,i,d);
        telemetry.addData("angular vel ", Control.GetImu().GetAngularVelocity());
        telemetry.addData("p ", p);
        telemetry.addData("i ", i);
        telemetry.addData("d ", d);
        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();
        packet.put("angular vel", Control.GetImu().GetAngularVelocity());
        packet.put("controller ", CInput.GetRJSX() * TurnWhileDrivingSpeed);
        packet.put("pid offset", Control.GetPID().getOutput(CInput.GetRJSX() * TurnWhileDrivingSpeed, Control.GetImu().GetAngularVelocity()));
        dashboard.sendTelemetryPacket(packet);
        //Return encoder value
        /*telemetry.addData("Encoder X ", Control.GetOdometry().GetEncoderVals()[0]);
        telemetry.addData("Encoder Y ", Control.GetOdometry().GetEncoderVals()[1]);
        telemetry.addData("Distance Gone ", Control.GetOdometry().CalculateDistance());*/
    }

    //Loop Methods
    private void MangeDriveMovement(){
        //MOVE if left joystick magnitude > 0.1
        if (CInput.CalculateLJSMag() > 0.1) {
            Control.RawDrive(CInput.CalculateLJSAngle(), CInput.CalculateLJSMag() * DriveSpeed, CInput.GetRJSX() * TurnWhileDrivingSpeed);//drives at (angle, speed, turnOffset)
            telemetry.addData("Moving at ", CInput.CalculateLJSAngle());
        }
        //TURN if right joystick magnitude > 0.1 and not moving
        else if (Math.abs(CInput.GetRJSX()) > 0.1) {
            Control.RawTurn(CInput.GetRJSX() * TurnSpeed);//turns at speed according to rjs1
            telemetry.addData("Turning", true);
        }
        else {
            Control.GetChassis().SetMotorSpeeds(0,0,0,0);
        }
    }
    /*private void ManageShooterIntake(){
        //INTAKE if right trigger pressed
        if(CInput.gamepad.right_trigger > 0.1){
            Control.Intake();
        }
        //SPIN UP if Y pressed
        if(CInput.gamepad.y){
            Control.SpinUpShooter();
        }
        //FIRE if B pressed
        if(CInput.gamepad.b){
            Control.FireShooter();
        }
    }*/

    @Override
    public void Started() {

    }

    @Override
    public void Stopped() {

    }

    @Override
    public void APressed() {
        //AIM SHOOTER if A pressed
        Control.AimShooter();
    }

    @Override
    public void BPressed() {

    }

    @Override
    public void XPressed() {
        Control.GetOdometry().Reset();
    }

    @Override
    public void YPressed() {

    }

    @Override
    public void AHeld() {

    }

    @Override
    public void BHeld() {

    }

    @Override
    public void XHeld() {

    }

    @Override
    public void YHeld() {

    }

    @Override
    public void AReleased() {

    }

    @Override
    public void BReleased() {

    }

    @Override
    public void XReleased() {

    }

    @Override
    public void YReleased() {

    }

    @Override
    public void LBPressed() {

    }

    @Override
    public void RBPressed() {

    }

    @Override
    public void LTPressed() {
        Busy = true;
        Control.Brake();
    }

    @Override
    public void RTPressed() {

    }

    @Override
    public void LBHeld() {

    }

    @Override
    public void RBHeld() {

    }

    @Override
    public void LTHeld() {

    }

    @Override
    public void RTHeld() {

    }

    @Override
    public void LBReleased() {

    }

    @Override
    public void RBReleased() {

    }

    @Override
    public void LTReleased() {
        Busy = false;
        Control.GetChassis().SetModeRunUsingEncoders();
    }

    @Override
    public void RTReleased() {

    }
}
