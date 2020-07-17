package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode._Archive.Input.EventInput;

import java.util.ArrayList;
import java.util.List;

public class ControllerInput extends EventInput
{
    //REFERENCES
    public Gamepad Gamepad1;
    public Gamepad Gamepad2;

    protected OpMode opMode;

    //A list of all the listeners
    private List<ControllerInputListener> listeners = new ArrayList<ControllerInputListener>();

    //Add a new listener
    public void addListener(ControllerInputListener toAdd) {
        listeners.add(toAdd);
    }

    //VARIABLES
    //misc.
    private double TriggerThreshold = 0.1;

    //buttons
    private boolean A1Down = false;
    private boolean B1Down = false;
    private boolean X1Down = false;
    private boolean Y1Down = false;
    private boolean LB1Down = false;
    private boolean RB1Down = false;
    private boolean LT1Down = false;
    private boolean RT1Down = false;
    private boolean A2Down = false;
    private boolean B2Down = false;
    private boolean X2Down = false;
    private boolean Y2Down = false;
    private boolean LB2Down = false;
    private boolean RB2Down = false;
    private boolean LT2Down = false;
    private boolean RT2Down = false;

    //GETTERS
    //Gamepad1
    public double GetLJSX1()
    {
        return Gamepad1.left_stick_x;
    }
    public double GetLJSY1()
    {
        return Gamepad1.left_stick_y;
    }
    public double GetRJSX1()
    {
        return Gamepad1.right_stick_x;
    }
    public double GetRJSY1()
    {
        return Gamepad1.right_stick_y;
    }
    //Gamepad2
    public double GetLJSX2()
    {
        return Gamepad2.left_stick_x;
    }
    public double GetLJSY2()
    {
        return Gamepad2.left_stick_y;
    }
    public double GetRJSX2()
    {
        return Gamepad2.right_stick_x;
    }
    public double GetRJSY2()
    {
        return Gamepad2.right_stick_y;
    }

    //EVENTS
    //Pressed
    public void A1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.A1Pressed();
    }
    public void B1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.B1Pressed();
    }
    public void X1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.X1Pressed();
    }
    public void Y1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.Y1Pressed();
    }
    public void A2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.A2Pressed();
    }
    public void B2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.B2Pressed();
    }
    public void X2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.X2Pressed();
    }
    public void Y2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.Y2Pressed();
    }
    public void RB1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RB1Pressed();
    }
    public void LB1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LB1Pressed();
    }
    public void RT1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RT1Pressed();
    }
    public void LT1Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LT1Pressed();
    }
    public void RB2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RB2Pressed();
    }
    public void LB2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LB2Pressed();
    }
    public void RT2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RT2Pressed();
    }
    public void LT2Pressed() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LT2Pressed();
    }

    //Released
    public void A1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.A1Released();
    }
    public void B1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.B1Released();
    }
    public void X1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.X1Released();
    }
    public void Y1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.Y1Released();
    }
    public void A2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.A2Released();
    }
    public void B2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.B2Released();
    }
    public void X2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.X2Released();
    }
    public void Y2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.Y2Released();
    }
    public void RB1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RB1Released();
    }
    public void LB1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LB1Released();
    }
    public void RT1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RT1Released();
    }
    public void LT1Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LT1Released();
    }
    public void RB2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RB2Released();
    }
    public void LB2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LB2Released();
    }
    public void RT2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.RT2Released();
    }
    public void LT2Released() {
        // Notify listeners
        for (ControllerInputListener CIL : listeners)
            CIL.LT2Released();
    }

    public ControllerInput(OpMode setOpMode){
        opMode = setOpMode;
    }

    @Override
    public void Init() {
        Gamepad1 = opMode.gamepad1;
        Gamepad2 = opMode.gamepad2;
    }

    public void Loop(){
        //DETECT EVENTS
        //Pressed
        //A1 button
        if(Gamepad1.a == true && A1Down == false){
            A1Pressed();
        }
        //B1 button
        if(Gamepad1.b == true && B1Down == false){
            B1Pressed();
        }
        //X1 button
        if(Gamepad1.x == true && X1Down == false){
            X1Pressed();
        }
        //Y1 button
        if(Gamepad1.y == true && Y1Down == false){
            Y1Pressed();
        }
        //A2 button
        if(Gamepad2.a == true && A2Down  == false){
            A2Pressed();
        }
        //B2 button
        if(Gamepad2.b == true && B2Down  == false){
            B2Pressed();
        }
        //X2 button
        if(Gamepad2.x == true && X2Down  == false){
            X1Pressed();
        }
        //Y2 button
        if(Gamepad2.y == true && Y2Down  == false){
            Y1Pressed();
        }
        //LB1 button
        if(Gamepad1.left_bumper == true && LB1Down  == false){
            LB1Pressed();
        }
        //RB1 button
        if(Gamepad1.right_bumper == true && RB1Down  == false){
            RB1Pressed();
        }
        //LT1 button
        if(Gamepad1.left_trigger > TriggerThreshold && LT1Down  == false){
            LT1Pressed();
        }
        //RT1 button
        if(Gamepad1.right_trigger > TriggerThreshold && RT1Down  == false){
            RT1Pressed();
        }
        //LB2 button
        if(Gamepad2.left_bumper == true && LB2Down  == false){
            LB2Pressed();
        }
        //RB2 button
        if(Gamepad2.right_bumper == true && RB2Down   == false){
            RB2Pressed();
        }
        //LT2 button
        if(Gamepad2.left_trigger > TriggerThreshold && LT2Down  == false){
            LT1Pressed();
        }
        //RT2 button
        if(Gamepad2.right_trigger > TriggerThreshold && RT2Down  == false){
            RT1Pressed();
        }


        //Released
        //A1 button
        if(Gamepad1.a == false && A1Down == true){
            A1Released();
        }
        //B1 button
        if(Gamepad1.b == false && B1Down == true){
            B1Released();
        }
        //X1 button
        if(Gamepad1.x == false && X1Down == true){
            X1Released();
        }
        //Y1 button
        if(Gamepad1.y == false && Y1Down == true){
            Y1Released();
        }
        //A2 button
        if(Gamepad2.a == false && A2Down  == true){
            A2Released();
        }
        //B2 button
        if(Gamepad2.b == false && B2Down  == true){
            B2Released();
        }
        //X2 button
        if(Gamepad2.x == false && X2Down  == true){
            X1Released();
        }
        //Y2 button
        if(Gamepad2.y == false && Y2Down  == true){
            Y1Released();
        }
        //LB1 button
        if(Gamepad1.left_bumper == false && LB1Down  == true){
            LB1Released();
        }
        //RB1 button
        if(Gamepad1.right_bumper == false && RB1Down  == true){
            RB1Released();
        }
        //LT1 button
        if(Gamepad1.left_trigger <= TriggerThreshold && LT1Down  == true){
            LT1Released();
        }
        //RT1 button
        if(Gamepad1.right_trigger <= TriggerThreshold && RT1Down  == true){
            RT1Released();
        }
        //LB2 button
        if(Gamepad2.left_bumper == false && LB2Down  == true){
            LB2Released();
        }
        //RB2 button
        if(Gamepad2.right_bumper == false && RB2Down   == true){
            RB2Released();
        }
        //LT2 button
        if(Gamepad2.left_trigger <= TriggerThreshold && LT2Down  == true){
            LT1Released();
        }
        //RT2 button
        if(Gamepad2.right_trigger <= TriggerThreshold && RT2Down  == true){
            RT1Released();
        }


        //SET VARS TO CURRENT VALUES
        A1Down  = Gamepad1.a;
        B1Down  = Gamepad1.b;
        X1Down  = Gamepad1.x;
        Y1Down  = Gamepad1.y;
        LB1Down  = Gamepad1.left_bumper;
        RB1Down  = Gamepad1.right_bumper;
        LT1Down  = Gamepad1.left_trigger > TriggerThreshold;
        RT1Down  = Gamepad1.right_trigger > TriggerThreshold;
        A2Down  = Gamepad1.a;
        B2Down  = Gamepad1.b;
        X2Down  = Gamepad1.x;
        Y2Down = Gamepad1.y;
        LB2Down   = Gamepad1.left_bumper;
        RB2Down  = Gamepad1.right_bumper;
        LT2Down  = Gamepad1.left_trigger > TriggerThreshold;
        RT2Down  = Gamepad1.right_trigger > TriggerThreshold;



    }

    public double CalculateLJSAngle1(){
        //Calculate angle of left joystick on gamepad 1
        double Y = GetRJSY1(); //X input
        double X = GetRJSX1(); //Y input
        double leftStickBaring = Math.atan2(Y,-X); //get measurement of joystick angle
        leftStickBaring = Math.toDegrees(leftStickBaring);
        leftStickBaring -= 90;
        if(leftStickBaring < 0)//convert degrees to positive if needed
        {
            leftStickBaring = 360 + leftStickBaring;
        }
        return leftStickBaring;
    }

    public double CalculateLJSMag1(){
        //Calculate magnitude of the left joystick on the right gamepad
        //Distance formula for calculating joystick power
        return Math.abs(Math.sqrt(Math.pow(GetLJSX1() - 0, 2) + Math.pow(GetLJSY1() - 0, 2)));
    }
}
