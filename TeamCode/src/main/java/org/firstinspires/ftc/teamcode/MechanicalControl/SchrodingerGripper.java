package org.firstinspires.ftc.teamcode.MechanicalControl;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class SchrodingerGripper
{
    ////DEPENDENCIES////
    //Servos
    private Servo gripperR, gripperL, headingServo, rotationServoR, rotationServoL;

    ////VARIABLES////
    //Universal
    public static double servoPosToDegrees = 1/180; //TODO: find this coefficient

    //Gripper Rotation
    //TODO- Find max and min of gripper rotation in degrees with intake as 0
    public static double rotationMax = 180;
    public static double rotationMin = 0;
    private double targetRotation;

    //Gripper Heading
    private double targetHeading;

    //Gripper Grabbing
    //TODO- Find max and min of gripper grabbers in degrees with closed as 0
    public static double grabOpen = 0.5;
    public static double grabClosed = 0;
    private double targetGrabPos;


    public void Init(Servo setGripperR, Servo setGripperL, Servo setHeadingServo, Servo setRotationServoR, Servo setRotationServoL){
        gripperR = setGripperR;
        gripperL = setGripperL;
        headingServo = setHeadingServo;
        rotationServoR = setRotationServoL;
        rotationServoL = setRotationServoL;
    }

    public void SetTargetHeading(double degrees){ }
    public void SetTargetRotation(double degrees){ }
    public void SetGrabberState(boolean closed){ }
    public void ResetGripper(){ }
}
