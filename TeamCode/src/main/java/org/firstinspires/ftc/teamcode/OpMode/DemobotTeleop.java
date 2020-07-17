package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.ControllerInput;
import org.firstinspires.ftc.teamcode.Core.ControllerInputListener;
import org.firstinspires.ftc.teamcode.Core.DemobotControl;

@TeleOp(name = "Demobot TeleOp")
public class DemobotTeleop extends OpMode implements ControllerInputListener
{
    //Dependencies
    DemobotControl Control;
    ControllerInput CInput;

    //Variable


    @Override
    public void init() {
        //Sets up demobot control class
        Control = new DemobotControl(this);
        Control.Init();

        //Sets up controller input
        CInput = new ControllerInput(this);
        CInput.addListener(this);
    }

    @Override
    public void start(){
        Control.Start();
    }

    @Override
    public void loop() {
        //Manage Robot Movement
        //Move
        if(Math.abs(CInput.GetRJSX1()) > 0.1 || Math.abs(CInput.GetRJSY1()) > 0.1){
            Control.RawDrive(CInput.CalculateLJSAngle1(),CInput.CalculateLJSMag1(),0);
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
