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

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.RoadrunnerControl;
import org.firstinspires.ftc.teamcode.Navigation.Tensorflow.TensorFlowObjectDetector;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

public class OrionNavigator
{
    private RoadrunnerControl rr;
    private VuMarkNavigation v;
    private TensorFlowObjectDetector tf;
    private RobotCoordinateSystem cs;
    private DemobotControl control;
    private OpMode opMode;

    public OrionNavigator(OpMode setOpMode, DemobotControl setControl){
        opMode = setOpMode;
        control = setControl;
    }

    public void Init(){
        rr = new RoadrunnerControl(opMode);
        v = new VuMarkNavigation(opMode);
        tf = new TensorFlowObjectDetector(opMode, v.GetVuforia());
        cs = new RobotCoordinateSystem(0,0,0);
    }

    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}

    public void GetVuforia(int vuforiaCode){
        double[] data = v.GetData(vuforiaCode);
        double high = data[0];
        double right = data[1];
        double ahead = data[2];
        double dist = data[3];
        double angle = data[4];
        opMode.telemetry.addData("vumark is ",dist + "milimeters away, "+angle+" degrees right, and "+high+" milimeters high.");
//        data[0] = tX;
//        data[1]= tY;
//        data[2] = tZ;
//        data[3] = dist;
//        data[4] = rZreal;
    }

    public void MoveToVumark(int vumarkIndex, double xOffset, double yOffset, double headingOffset, double xThreshold, double yThreshold){
        //Move to an offset relative to a vumark and face it- useful for shooting

        //Find vumark pose
        double[] vumarkDist = v.GetData(vumarkIndex);
        //Find current robot pose
        Pose2d robotPose = rr.GetCurrentPose();

        //Find offset from vumark in local space
        double offsetX = xOffset - vumarkDist[0];
        double offsetY = yOffset - vumarkDist[1];
        double offsetH = headingOffset - vumarkDist[2];

        cs.SetRobotGlobalPose(robotPose.getX(), robotPose.getY(), robotPose.getHeading());
        double[] globalOffset = cs.ConvertToGlobal(offsetX, offsetY, offsetH);

        //Move spline
        MoveSpline(globalOffset[0], globalOffset[1], 0);
        //Turn to face target
        Turn(globalOffset[2]);
    }

    public void GoToDisc(){
        //Get disc's offset
        double[] offset = tf.GetClosestDisc();
        //Calculate angular offset

    }
}
