package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.ControllerInput;
import org.firstinspires.ftc.teamcode.Core.ControllerInputListener;
import org.firstinspires.ftc.teamcode.Core.DemobotControl;

//The class for controlling the robot in teleop. Includes basic drive movement, shooter operations,
//and advanced autonomous functions.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit
//REQUIRED TO FUNCTION: Controllers

@TeleOp(name = "Demobot TeleOp")
public class DemobotTeleop extends OpMode implements ControllerInputListener
{
    ////Dependencies////
    private DemobotControl Control;
    private ControllerInput CInput;

    ////Variables////
    //Tweaking Vars
    private double TurnWhileDrivingSpeed = 1;//used to change how fast robot turns when driving
    private double DriveSpeed = 1;//used to change how fast robot drives
    private double TurnSpeed = 1;//used to change how fast robot turns
    //Utility Vars
    private boolean Busy = false;


    @Override
    public void init() {
        //Sets up demobot control class
        Control = new DemobotControl(this);
        Control.Init();

        //Sets up controller input
        CInput = new ControllerInput(this);
        CInput.Init();
        CInput.addListener(this);
    }

    @Override
    public void start(){
        Control.Start();
    }

    @Override
    public void loop() {
        //Only run if robot isn't busy
        if(!Busy) {
            ////Manage Robot Movement////
            //MOVE if left joystick magnitude > 0.1
            if (CInput.CalculateLJSMag1() > 0.1) {
                Control.RawDrive(CInput.CalculateLJSAngle1(), CInput.CalculateLJSMag1() * DriveSpeed, CInput.GetRJSX1() * TurnWhileDrivingSpeed);//(angle, speed, turnOffset)
            }
            //TURN if right joystick magnitude > 0.1 and not moving
            else if (Math.abs(CInput.GetRJSX1()) > 0.1) {
                Control.RawTurn(CInput.GetRJSX1() * TurnSpeed);//turns at speed according to rjs1
            }
        }
    }

    @Override
    public void Started() {

    }

    @Override
    public void Stopped() {

    }

    @Override
    public void A1Pressed() {

    }

    @Override
    public void B1Pressed() {

    }

    @Override
    public void X1Pressed() {

    }

    @Override
    public void Y1Pressed() {

    }

    @Override
    public void A1Released() {

    }

    @Override
    public void B1Released() {

    }

    @Override
    public void X1Released() {

    }

    @Override
    public void Y1Released() {

    }

    @Override
    public void LB1Pressed() {

    }

    @Override
    public void RB1Pressed() {

    }

    @Override
    public void LT1Pressed() {
        Busy = true;
        Control.Brake();
    }

    @Override
    public void RT1Pressed() {

    }

    @Override
    public void LB1Released() {

    }

    @Override
    public void RB1Released() {

    }

    @Override
    public void LT1Released() {
        Busy = false;
        Control.GetChassis().SetModeRunUsingEncoders();
    }

    @Override
    public void RT1Released() {

    }

    @Override
    public void A2Pressed() {

    }

    @Override
    public void B2Pressed() {

    }

    @Override
    public void X2Pressed() {

    }

    @Override
    public void Y2Pressed() {

    }

    @Override
    public void A2Released() {

    }

    @Override
    public void B2Released() {

    }

    @Override
    public void X2Released() {

    }

    @Override
    public void Y2Released() {

    }

    @Override
    public void LB2Pressed() {

    }

    @Override
    public void RB2Pressed() {

    }

    @Override
    public void LT2Pressed() {

    }

    @Override
    public void RT2Pressed() {

    }

    @Override
    public void LB2Released() {

    }

    @Override
    public void RB2Released() {

    }

    @Override
    public void LT2Released() {

    }

    @Override
    public void RT2Released() {

    }
}
