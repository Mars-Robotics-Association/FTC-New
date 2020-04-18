package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.*;

class AA_ControllerInput extends A_External
{
    //REFERENCES
    private OpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    //LOCAL MEMBER VARIABLES
    //misc.
    private double triggerThreshold = 0.1;

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

    //A list of all the listeners
    private List<AB_ControllerInputListener> listeners = new ArrayList<AB_ControllerInputListener>();

    //Add a new listener
    public void addListener(AB_ControllerInputListener toAdd) {
        listeners.add(toAdd);
    }

    //EVENTS
    //Pressed
    public void A1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.A1Pressed();
    }
    public void B1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.B1Pressed();
    }
    public void X1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X1Pressed();
    }
    public void Y1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.Y1Pressed();
    }
    public void A2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.A2Pressed();
    }
    public void B2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.B2Pressed();
    }
    public void X2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X2Pressed();
    }
    public void Y2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.Y2Pressed();
    }
    public void RB1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RB1Pressed();
    }
    public void LB1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LB1Pressed();
    }
    public void RT1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RT1Pressed();
    }
    public void LT1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LT1Pressed();
    }
    public void RB2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RB2Pressed();
    }
    public void LB2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LB2Pressed();
    }
    public void RT2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RT2Pressed();
    }
    public void LT2Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LT2Pressed();
    }

    //Released
    public void A1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.A1Released();
    }
    public void B1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.B1Released();
    }
    public void X1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X1Released();
    }
    public void Y1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.Y1Released();
    }
    public void A2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.A2Released();
    }
    public void B2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.B2Released();
    }
    public void X2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X2Released();
    }
    public void Y2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.Y2Released();
    }
    public void RB1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RB1Released();
    }
    public void LB1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LB1Released();
    }
    public void RT1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RT1Released();
    }
    public void LT1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LT1Released();
    }
    public void RB2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RB2Released();
    }
    public void LB2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LB2Released();
    }
    public void RT2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.RT2Released();
    }
    public void LT2Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.LT2Released();
    }

    public AA_ControllerInput (OpMode setOpMode){
        opMode = setOpMode;
    }

    @Override
    public void Init() {
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
    }

    public void Loop(){
        //DETECT EVENTS
        //Pressed
        //A1 button
        if(gamepad1.a == true && A1Down == false){
            A1Pressed();
        }
        //B1 button
        if(gamepad1.b == true && B1Down == false){
            B1Pressed();
        }
        //X1 button
        if(gamepad1.x == true && X1Down == false){
            X1Pressed();
        }
        //Y1 button
        if(gamepad1.y == true && Y1Down == false){
            Y1Pressed();
        }
        //A2 button
        if(gamepad2.a == true && A2Down  == false){
            A2Pressed();
        }
        //B2 button
        if(gamepad2.b == true && B2Down  == false){
            B2Pressed();
        }
        //X2 button
        if(gamepad2.x == true && X2Down  == false){
            X1Pressed();
        }
        //Y2 button
        if(gamepad2.y == true && Y2Down  == false){
            Y1Pressed();
        }
        //LB1 button
        if(gamepad1.left_bumper == true && LB1Down  == false){
            LB1Pressed();
        }
        //RB1 button
        if(gamepad1.right_bumper == true && RB1Down  == false){
            RB1Pressed();
        }
        //LT1 button
        if(gamepad1.left_trigger > triggerThreshold && LT1Down  == false){
            LT1Pressed();
        }
        //RT1 button
        if(gamepad1.right_trigger > triggerThreshold && RT1Down  == false){
            RT1Pressed();
        }
        //LB2 button
        if(gamepad2.left_bumper == true && LB2Down  == false){
            LB2Pressed();
        }
        //RB2 button
        if(gamepad2.right_bumper == true && RB2Down   == false){
            RB2Pressed();
        }
        //LT2 button
        if(gamepad2.left_trigger > triggerThreshold && LT2Down  == false){
            LT1Pressed();
        }
        //RT2 button
        if(gamepad2.right_trigger > triggerThreshold && RT2Down  == false){
            RT1Pressed();
        }


        //Released
        //A1 button
        if(gamepad1.a == false && A1Down == true){
            A1Released();
        }
        //B1 button
        if(gamepad1.b == false && B1Down == true){
            B1Released();
        }
        //X1 button
        if(gamepad1.x == false && X1Down == true){
            X1Released();
        }
        //Y1 button
        if(gamepad1.y == false && Y1Down == true){
            Y1Released();
        }
        //A2 button
        if(gamepad2.a == false && A2Down  == true){
            A2Released();
        }
        //B2 button
        if(gamepad2.b == false && B2Down  == true){
            B2Released();
        }
        //X2 button
        if(gamepad2.x == false && X2Down  == true){
            X1Released();
        }
        //Y2 button
        if(gamepad2.y == false && Y2Down  == true){
            Y1Released();
        }
        //LB1 button
        if(gamepad1.left_bumper == false && LB1Down  == true){
            LB1Released();
        }
        //RB1 button
        if(gamepad1.right_bumper == false && RB1Down  == true){
            RB1Released();
        }
        //LT1 button
        if(gamepad1.left_trigger <= triggerThreshold && LT1Down  == true){
            LT1Released();
        }
        //RT1 button
        if(gamepad1.right_trigger <= triggerThreshold && RT1Down  == true){
            RT1Released();
        }
        //LB2 button
        if(gamepad2.left_bumper == false && LB2Down  == true){
            LB2Released();
        }
        //RB2 button
        if(gamepad2.right_bumper == false && RB2Down   == true){
            RB2Released();
        }
        //LT2 button
        if(gamepad2.left_trigger <= triggerThreshold && LT2Down  == true){
            LT1Released();
        }
        //RT2 button
        if(gamepad2.right_trigger <= triggerThreshold && RT2Down  == true){
            RT1Released();
        }


        //SET VARS TO CURRENT VALUES
        A1Down  = gamepad1.a;
        B1Down  = gamepad1.b;
        X1Down  = gamepad1.x;
        Y1Down  = gamepad1.y;
        LB1Down  = gamepad1.left_bumper;
        RB1Down  = gamepad1.right_bumper;
        LT1Down  = gamepad1.left_trigger > triggerThreshold;
        RT1Down  = gamepad1.right_trigger > triggerThreshold;
        A2Down  = gamepad1.a;
        B2Down  = gamepad1.b;
        X2Down  = gamepad1.x;
        Y2Down = gamepad1.y;
        LB2Down   = gamepad1.left_bumper;
        RB2Down  = gamepad1.right_bumper;
        LT2Down  = gamepad1.left_trigger > triggerThreshold;
        RT2Down  = gamepad1.right_trigger > triggerThreshold;

    }
}