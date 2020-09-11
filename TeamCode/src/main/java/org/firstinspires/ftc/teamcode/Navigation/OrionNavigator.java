package org.firstinspires.ftc.teamcode.Navigation;

////SENSING////
//Vuforia.java
//Tensorflow Package
//DistanceSensorArray.java
//ColorSensorArray.java

////DRIVING////
//Roadrunner Package
//Orion Package

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.RoadrunnerControl;

public class OrionNavigator
{
    private RoadrunnerControl rr;
    private OpMode opMode;

    public OrionNavigator(OpMode setOpMode){
        opMode = setOpMode;
    }

    public void Init(){ rr = new RoadrunnerControl(opMode); }

    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}
}
