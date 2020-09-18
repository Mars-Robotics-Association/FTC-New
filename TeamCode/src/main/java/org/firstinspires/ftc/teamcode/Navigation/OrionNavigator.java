package org.firstinspires.ftc.teamcode.Navigation;

////SENSING////
//Vuforia.java
//Tensorflow Package
//DistanceSensorArray.java
//ColorSensorArray.java

////DRIVING////
//Roadrunner Package
//Orion Package

import com.acmerobotics.roadrunner.geometry.Pose2d;
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

        //Find vumark pose
        double[] vumarkDist = v.GetData(vumarkIndex);
        //Find current robot pose
        Pose2d robotPose = rr.GetCurrentPose();

        //Find offset from vumark in world space //TODO: make dynamic with start pos of robot
        double offsetX = xOffset - vumarkDist[0] + robotPose.getX();
        double offsetY = yOffset - vumarkDist[1] + robotPose.getY();
        double offsetH = headingOffset - vumarkDist[2] + robotPose.getHeading();

        //Move spline
        MoveSpline(offsetX, offsetY, 0);
        //Turn to face target
        Turn(offsetH);
    }
}
