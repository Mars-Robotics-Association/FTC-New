package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.Gamepad;


import java.util.ArrayList;
import java.util.List;

//Calls event based commands from the controllers as well as providing direct access. To use, make a
//class interface to ControllerInputListener.java, create an instance of this class, call input.AddListener(this),
//then input.Init(), then there will be errors so click on red light bulb and hit "implement methods".

//REQUIRED TO RUN: None
//REQUIRED TO FUNCTION: Controllers

public class ControllerInput
{
    //REFERENCES
    public Gamepad gamepad;
    
    //A list of all the listeners
    private List<ControllerInputListener> listeners = new ArrayList<ControllerInputListener>();

    //Add a new listener
    public void addListener(ControllerInputListener toAdd) {
        listeners.add(toAdd);
    }
    
    //Initializer
    public ControllerInput(Gamepad setGamepad){
        this.gamepad = setGamepad;
    }

    //VARIABLES
    //misc.
    private double TriggerThreshold = 0.1;

    //buttons
    private boolean ADown = false;
    private boolean BDown = false;
    private boolean XDown = false;
    private boolean YDown = false;
    private boolean LBDown = false;
    private boolean RBDown = false;
    private boolean LTDown = false;
    private boolean RTDown = false;

    //GETTERS
    public double GetLJSX()
    {
        return gamepad.left_stick_x;
    }
    public double GetLJSY()
    {
        return gamepad.left_stick_y;
    }
    public double GetRJSX()
    {
        return gamepad.right_stick_x;
    }
    public double GetRJSY()
    {
        return gamepad.right_stick_y;
    }

    ////EVENTS////
    //Pressed
    public void APressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.APressed();
    }
    public void BPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.BPressed();
    }
    public void XPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.XPressed();
    }
    public void YPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.YPressed();
    }
    public void RBPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RBPressed();
    }
    public void LBPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.LBPressed();
    }
    public void RTPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RTPressed();
    }
    public void LTPressed() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.LTPressed();
    }

    //Held
    public void AHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.AHeld();
    }
    public void BHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.BHeld();
    }
    public void XHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.XHeld();
    }
    public void YHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.YHeld();
    }
    public void RBHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RBHeld();
    }
    public void LBHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.LBHeld();
    }
    public void RTHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RTHeld();
    }
    public void LTHeld() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.LTHeld();
    }

    //Released
    public void AReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.AReleased();
    }
    public void BReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.BReleased();
    }
    public void XReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.XReleased();
    }
    public void YReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.YReleased();
    }
    public void RBReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RBReleased();
    }
    public void LBReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.LBReleased();
    }
    public void RTReleased() {
        for (ControllerInputListener controllerInput : listeners)
            controllerInput.RTReleased();
    }
    public void LTReleased() {
        for (ControllerInputListener inputListener : listeners)
            inputListener.LTReleased();
    }

    public void Loop(){
        //DETECT EVENTS
        //Pressed
        if(gamepad.a == true && ADown == false) APressed();
        if(gamepad.b == true && BDown == false) BPressed();
        if(gamepad.x == true && XDown == false) XPressed();
        if(gamepad.y == true && YDown == false) YPressed();
        if(gamepad.left_bumper == true && LBDown == false) LBPressed();
        if(gamepad.right_bumper == true && RBDown == false) RBPressed();
        if(gamepad.left_trigger > TriggerThreshold && LTDown == false) LTPressed();
        if(gamepad.right_trigger > TriggerThreshold && RTDown == false) RTPressed();

        //Held
        if(gamepad.a == true) AHeld();
        if(gamepad.b == true) BHeld();
        if(gamepad.x == true) XHeld();
        if(gamepad.y == true) YHeld();
        if(gamepad.left_bumper == true) LBHeld();
        if(gamepad.right_bumper == true) RBHeld();
        if(gamepad.left_trigger > TriggerThreshold) LTHeld();
        if(gamepad.right_trigger > TriggerThreshold) RTHeld();

        //Released
        if(gamepad.a == false && ADown == true) AReleased();
        if(gamepad.b == false && BDown == true) BReleased();
        if(gamepad.x == false && XDown == true) XReleased();
        if(gamepad.y == false && YDown == true) YReleased();
        if(gamepad.left_bumper == false && LBDown == true) LBReleased();
        if(gamepad.right_bumper == false && RBDown == true) RBReleased();
        if(gamepad.left_trigger <= TriggerThreshold && LTDown == true) LTReleased();
        if(gamepad.right_trigger <= TriggerThreshold && RTDown == true) RTReleased();


        //SET VARS TO CURRENT VALUES
        ADown = gamepad.a;
        BDown = gamepad.b;
        XDown = gamepad.x;
        YDown = gamepad.y;
        LBDown = gamepad.left_bumper;
        RBDown = gamepad.right_bumper;
        LTDown = gamepad.left_trigger > TriggerThreshold;
        RTDown = gamepad.right_trigger > TriggerThreshold;
    }

    public double CalculateLJSAngle(){
        //Calculate angle of left joystick
        double Y = gamepad.left_stick_y; //X input
        double X = gamepad.left_stick_x; //Y input

        //return telemetry for debug
        /*opMode.telemetry.addData("Joystick X ", X);
        opMode.telemetry.addData("Joystick Y ", Y);*/

        double leftStickBaring = Math.atan2(Y,X); //get measurement of joystick angle
        leftStickBaring = Math.toDegrees(leftStickBaring);
        leftStickBaring -= 90;
        if(leftStickBaring < 0)//convert degrees to positive if needed
        {
            leftStickBaring = 360 + leftStickBaring;
        }
        return leftStickBaring;
    }

    public double CalculateLJSMag(){
        //Calculate magnitude of the left joystick
        //Distance formula for calculating joystick power
        return Math.abs(Math.sqrt(Math.pow(GetLJSX() - 0, 2) + Math.pow(GetLJSY() - 0, 2)));
    }
}
