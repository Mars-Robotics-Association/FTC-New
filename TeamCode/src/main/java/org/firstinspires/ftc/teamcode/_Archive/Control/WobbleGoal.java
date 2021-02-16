package org.firstinspire.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

public class WobbleGoal extends Control {

    //These had to go first because Java doesn't have hoisting
    CRServo LiftServo;
    LiftServo = hardwareMap.crservo.get("liftservo_servo");

    Servo LeftArm;
    Servo = hardwareMap.servo.get("LeftArm");

    Servo RightArm;
    Servo = hardwareMap.servo.get("RightArm");

    //  \/These you CAN change.\/
    private double LiftMaxSpeed = 6;
    private double LiftPower = 0;
    private int LiftDirection = 0;//<-- Changing this one won't do anything

    private double LeftWobbleGrab = 2/9;//40 Degrees
    private double LeftRingGrab = 1/9;//20 Degrees

    private double RightWobbleGrab = 1/9;//20 Degrees
    private double RightRingGrab = 2/9;//40 Degrees

    //  \/Don't mess with this area.\/
    DigitalChannel LowerSensor;
    digitalTouch = hardwareMap.get(DigitalChannel.class, "lowersensor_digital");
    LowerSensor.setMode(DigitalChannel.Mode.INPUT);

    DigitalChannel UpperSensor;
    digitalTouch = hardwareMap.get(DigitalChannel.class, "uppersensor_digital");
    UpperSensor.setMode(DigitalChannel.Mode.INPUT);

    public void SetWobbleLiftPower(double power){
        LiftPower = power;
        LiftDirection = 0;
    }
    //Simple setter function for the lift arm. Can be used to force stop raising and lowering.
    
    public void RaiseWobbleLift(){
        LiftDirection = 1;
        LiftPower = LiftMaxSpeed;
    }
    //Automatically raises the lift to the top. Stopped by SetWobbleLiftPower().

    public void LowerWobbleLift(){
        LiftDirection = -1;
        LiftPower = LiftMaxSpeed;
    }
    //Automatically lowers the lift to the bottom. Stopped by SetWobbleLiftPower().

    private String LastObject = "nothing";

    public void GrabObject(String item){
        if(item === "Ring"){
            LeftArm.setPosition(LeftRingGrab);
            RightArm.setPosition(RightRingGrab);
        } else if(item === "Wobble Goal"){
            LeftArm.setPosition(LeftWobbleGrab);
            RightArm.setPosition(RightWobbleGrab);
        } else {
            telemetry.addData("Error: Invalid item");
            telemetry.update;
        }
        LastObject = item;
    }
    //Grabs the object specified.

    public void ReleaseObject(){
        item = LastObject;
        if(item === "Ring"){
            LeftArm.setPosition(round(LeftRingGrab));
            RightArm.setPosition(round(RightRingGrab));
        } else if(item === "WobbleGoal"){
            LeftArm.setPosition(round(LeftWobbleGrab));
            RightArm.setPosition(round(RightWobbleGrab));
        } else{
            telemetry.addData("Error: Invalid item");
            telemetry.update;
        }
    }
    /*
        Releases the objecObt specified by the last call of Grabject. If you haven't
        called GrabObject recently, it sends a telemetry message and does nothing.
    */

    public void start() {
        LastObject = "WobbleGoal";//Don't do this.
        ReleaseObject();
    }

    public void Loop() {
        bool LowerPressed = !LowerSensor.getState(), UpperPressed = !UpperSensor.getState();
        if(UpperPressed||LowerPressed){
            LiftServo.setSpeed(0);
        } else{
            LiftServo.setSpeed(LiftPower);
        }
        if(LowerPressed && LiftDirection === -1){
            //Stops at the bottom
            LiftDirection = 0;
        } else if(UpperPressed && LiftDirection === 1){
            //Stops at the top
            LiftDirection = 0;
        } else if((UpperPressed||LowerPressed) && LiftDirection !== 0){
            //Flips direction of motor in case it was installed incorrectly
            LiftMaxSpeed *= -1;
            LiftDirection = 0;
        }
    }
}