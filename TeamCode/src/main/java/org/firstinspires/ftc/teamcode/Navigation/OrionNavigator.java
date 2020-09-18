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
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

public class OrionNavigator
{
    private RoadrunnerControl rr;
    private VuMarkNavigation v;
    private OpMode opMode;

    public OrionNavigator(OpMode setOpMode){
        opMode = setOpMode;
    }

    public void Init(){
        rr = new RoadrunnerControl(opMode);
        v = new VuMarkNavigation(opMode);
    }

    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}

    public void MoveToVumark(int vumarkIndex, double xOffset, double yOffset, double headingOffset, double xThreshold, double yThreshold){
        //Move to an offset relative to a vumark and face it- useful for shooting

        //Find vumark dist
        double[] vumarkDist = v.GetData(vumarkIndex);
        //Find offset from vumark
        double offsetX = vumarkDist[0] - xOffset;
        double offsetY = vumarkDist[1] - yOffset;
        double offsetH = vumarkDist[2] - headingOffset;
        //Move spline
        MoveSpline(offsetX, offsetY, 0);
        //Turn to face target
        Turn(offsetH);
    }
}
