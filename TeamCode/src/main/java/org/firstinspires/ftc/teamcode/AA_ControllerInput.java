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
    //buttons
    private boolean X1Down = false;

    //A list of all the listeners
    private List<AB_ControllerInputListener> listeners = new ArrayList<AB_ControllerInputListener>();

    //Add a new listener
    public void addListener(AB_ControllerInputListener toAdd) {
        listeners.add(toAdd);
    }

    //EVENTS
    //Pressed
    public void X1Pressed() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X1Pressed();
    }

    //Released
    public void X1Released() {
        // Notify listeners
        for (AB_ControllerInputListener CIL : listeners)
            CIL.X1Released();
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
        //x1 button
        if(gamepad1.x == true && X1Down == false){
            X1Pressed();
        }


        //Released
        //x1 button
        if(gamepad1.x == false && X1Down == true){
            X1Pressed();
        }


        //SET VARS TO CURRENT VALUES
        X1Down = gamepad1.x;
    }
}
