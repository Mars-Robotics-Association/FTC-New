package org.firstinspires.ftc.teamcode.Navigation;

////SENSING////
//Vuforia.java
//Tensorflow Package
//DistanceSensorArray.java
//ColorSensorArray.java

////DRIVING////
//Roadrunner Package
//Orion Package

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.RoadrunnerControl;
import org.firstinspires.ftc.teamcode.Navigation.Tensorflow.TensorFlowObjectDetector;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

import java.util.List;

public class OrionNavigator
{
    //TODO ====REFERENCES====
    private RoadrunnerControl rr;
    private VuMarkNavigation v;
    private TensorFlowObjectDetector tf;
    private RobotTransformSystem cs;
    private DemobotControl control;
    private OpMode opMode;

    //TODO ====VARIABLES====
    private double tfDistCoefficient = 1;
    private double tfXCoefficient = 1;
    public void SetTFCoefficients(double distCoefficient, double xCoefficient){
        tfDistCoefficient = distCoefficient;
        tfXCoefficient = xCoefficient;
    }


    public OrionNavigator(OpMode setOpMode, DemobotControl setControl){
        opMode = setOpMode;
        control = setControl;
    }

    public void Init(){
        if(control.isUSE_CHASSIS()) {
            rr = new RoadrunnerControl(opMode);
            rr.Init();
        }
        v = new VuMarkNavigation(opMode);
        tf = new TensorFlowObjectDetector(opMode, v.GetVuforia(), new double[]{0,0,0});
        cs = new RobotTransformSystem(0,0,0);
    }

    //TODO ====SIMPLE METHODS====
    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}
    public void SetPose(double x, double y, double heading){if(control.isUSE_CHASSIS())rr.SetPose(x,y,heading);}

    //TODO ====COMPLEX METHODS====
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

    //TODO: ====TELEMETRY METHODS FOR DEBUG====
    public void PrintVuforiaTelemetry(int vuforiaCode){
        double[] data = v.GetData(vuforiaCode);
        opMode.telemetry.addData("vumark is ",data[3] + " inches away, "+data[4]+" degrees right, and "+data[0]+" inches high.");
    }
    public void PrintTensorflowTelemetry(){
        opMode.telemetry.addLine("===ALL TF OBJECTS===");
        List<Recognition> tfObjs = tf.GetRecognitions();
        opMode.telemetry.addLine("===TF GetClosestDiscXYAngle() DATA===");
        opMode.telemetry.addLine(tf.GetClosestDiscXYAngle(tfDistCoefficient,tfXCoefficient).toString());
    }
}
