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
import org.firstinspires.ftc.teamcode.Core.RobotControl;
import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.RoadrunnerControl;
import org.firstinspires.ftc.teamcode.Navigation.Sensors.Arrays.DemobotSensorArray;
import org.firstinspires.ftc.teamcode.Navigation.Tensorflow.TensorFlowObjectDetector;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

public class OrionNavigator
{
    //TODO ====REFERENCES====
    private RoadrunnerControl rr;
    private VuMarkNavigation vn;
    private TensorFlowObjectDetector tf;
    private DemobotSensorArray sa;
    private RobotTransformSystem cs;
    private RobotControl control;
    private OpMode opMode;

    //TODO ====VARIABLES====
    private double tfDistCoefficient = 1;
    private double tfXCoefficient = 1;
    public void SetTFCoefficients(double distCoefficient, double xCoefficient){
        tfDistCoefficient = distCoefficient;
        tfXCoefficient = xCoefficient;
    }


    public OrionNavigator(OpMode setOpMode, RobotControl setControl){
        opMode = setOpMode;
        control = setControl;
    }

    public void Init(){
        if(control.isUSE_CHASSIS()) {
            rr = new RoadrunnerControl(opMode);
            rr.Init();
        }
        vn = new VuMarkNavigation(opMode);
        tf = new TensorFlowObjectDetector(opMode, vn.GetVuforia(), new double[]{0,0,0});
        cs = new RobotTransformSystem(0,0,0);
        //sa = new DemobotSensorArray(opMode);
    }

    //TODO ====SIMPLE METHODS====
    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}
    public void MoveLinear(double x, double y, double heading){rr.MoveLine(x,y,heading);}
    public void SetPose(double x, double y, double heading){
        if(control.isUSE_CHASSIS())rr.SetPose(x,y,heading);
        cs.SetRobotGlobalPose(x,y,heading);
    }
    public void UpdatePose(){
        if(control.isUSE_CHASSIS()){
            Pose2d robotPose = rr.GetCurrentPose();
            cs.SetRobotGlobalPose(robotPose.getX(), robotPose.getY(), robotPose.getHeading());
        }
    }

    //TODO ====COMPLEX METHODS====
    public void MoveToVumark(int vumarkIndex, double xOffset, double yOffset, double headingOffset, double xThreshold, double yThreshold){
        //Move to an offset relative to a vumark and face it- useful for shooting
        //Find vumark pose
        double[] vumarkDist = vn.GetData(vumarkIndex);

        //Find offset from vumark in local space
        double offsetX = xOffset - vumarkDist[0];
        double offsetY = yOffset - vumarkDist[2];
        double offsetH = headingOffset + vumarkDist[5];

        //Update pose and covert offset to global space
        UpdatePose();
        double[] globalOffset = cs.ConvertToGlobalComplex(offsetX, offsetY, offsetH);

        //Return telemetry
        opMode.telemetry.addData("## Vumark Global Pos ##   ", "(" + globalOffset[0] + ", " + globalOffset[1] + ", " + globalOffset[2] + ")");

        //Move spline if using chassis
        if(control.isUSE_CHASSIS()) MoveSpline(globalOffset[0], globalOffset[1], 0);
        //Turn to face target if using chassis
        if(control.isUSE_CHASSIS()) Turn(globalOffset[2]);
    }

    public void GoToDisc(){
        //Update robot pose
        UpdatePose();
        //Get disc's offset
        double[] offset = tf.GetClosestDiscXYAngleLocal(tfDistCoefficient, tfXCoefficient);
        double[] globalOffset = cs.ConvertToGlobalComplex(offset[0], offset[1], offset[2]);
        opMode.telemetry.addLine("===TF GoToDisc() DATA===");
        opMode.telemetry.addLine("x: " + globalOffset[0] + " y: " + globalOffset[1] + " angle: " + globalOffset[2]);
        //Turn to face disc
        if(control.isUSE_CHASSIS()) Turn(globalOffset[2]);
        //Move to intake disc
        if(control.isUSE_CHASSIS()) rr.MoveSpline(globalOffset[0], globalOffset[1], 0);
    }

    public int GetNumberOfDiscs(){return tf.ReturnNumberOfDiscsInSight();}

    //TODO: ====TELEMETRY METHODS FOR DEBUG====
    public void PrintVuforiaTelemetry(int vuforiaCode){
        double[] data = vn.GetData(vuforiaCode);
        opMode.telemetry.addData("vumark is ",data[3] + " inches away, "+data[4]+" degrees right, and "+data[0]+" inches high.");
    }
    public void PrintTensorflowTelemetry(){
        //opMode.telemetry.addLine("===ALL TF OBJECTS===");
        //List<Recognition> tfObjs = tf.GetRecognitions();
        opMode.telemetry.addLine("===TF GetClosestDiscXYAngle() DATA===");
        double[] data = tf.GetClosestDiscXYAngleLocal(tfDistCoefficient,tfXCoefficient);
        //data = cs.ConvertToGlobalComplex(data[0], data[1], data[2]);
        opMode.telemetry.addLine("x: " + data[0] + " y: " + data[1] + " angle: " + data[2]);
    }
}
