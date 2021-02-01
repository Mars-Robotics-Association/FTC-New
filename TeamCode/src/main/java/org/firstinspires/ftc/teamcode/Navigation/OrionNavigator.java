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

import org.firstinspires.ftc.teamcode.Core.Robots.MecanumBaseControl;
import org.firstinspires.ftc.teamcode.Navigation.Roadrunner.RoadrunnerControl;
import org.firstinspires.ftc.teamcode.Sensors.Arrays.DemobotSensorArray;
import org.firstinspires.ftc.teamcode.Navigation.Tensorflow.TensorFlowObjectDetector;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

public class OrionNavigator
{
    //TODO ====REFERENCES====
    private RoadrunnerControl rr;
    private VuMarkNavigation vuforiaFront;
    private VuMarkNavigation vuforiaBack;
    private TensorFlowObjectDetector tf;
    private DemobotSensorArray sa;
    private RobotTransformSystem cs;
    private MecanumBaseControl control;
    private OpMode opMode;

    //TODO ====VARIABLES====
    private double tfDistCoefficient = 1;
    private double tfXCoefficient = 1;
    public void SetTFCoefficients(double distCoefficient, double xCoefficient){
        tfDistCoefficient = distCoefficient;
        tfXCoefficient = xCoefficient;
    }


    public OrionNavigator(OpMode setOpMode, MecanumBaseControl setControl){
        opMode = setOpMode;
        control = setControl;
    }

    public void Init(){
        if(control.isUSE_CHASSIS()) {
            rr = new RoadrunnerControl(opMode);
            rr.Init();
        }
        vuforiaFront = new VuMarkNavigation(opMode, "Webcam 1");
        //vuforiaBack = new VuMarkNavigation(opMode, "Webcam 2");
        tf = new TensorFlowObjectDetector(opMode, vuforiaFront.GetVuforia(), new double[]{0,0,0});
        cs = new RobotTransformSystem(0,0,0);
        //sa = new DemobotSensorArray(opMode);
    }

    //TODO ====SIMPLE METHODS====
    public void Turn(double angle){rr.Turn(angle);}
    public void MoveSpline(double x, double y, double tangent){rr.MoveSpline(x,y,tangent);}
    public void MoveSplineConstHeading(double x, double y, double tangent){rr.MoveSplineConstantHeading(x,y,tangent);}
    public void MoveLinear(double x, double y, double heading){rr.MoveLine(x,y,heading);}
    public void SetPose(double x, double y, double heading){
        if(control.isUSE_CHASSIS())rr.SetPose(x,y,heading);
        cs.SetRobotGlobalPose(x,y,heading);
    }
    public Pose2d GetPose(){
        if(control.isUSE_CHASSIS()) return rr.GetCurrentPose();
        else return null;
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
        double[] vumarkDist = vuforiaFront.GetData(vumarkIndex);

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

    /**
     * Moves the robot towards the closest visible disc. Does not adjust using heading, but rather
     * by using side-to-side strafing.
     *
     * @param  speed  the speed at which to move the robot.
     * @param  correctionCoefficient  a multiplier for that speed to be calibrated for each robot. Values should be low.
     */
    public void MoveTowardsDiscRaw(double speed, double correctionCoefficient){
        UpdatePose();
        double error = tf.GetClosestDisc()[0] * correctionCoefficient;
        if(error == 0) return;
        rr.MoveRaw(new Pose2d(speed, error, rr.GetCurrentPose().getHeading())); //try to keep disc in center of screen
    }

    public double TurnTowardsDiscSpeed(double correctionCoefficient){
        UpdatePose();
        double error = tf.GetClosestDisc()[0];
        if(error == 0) return 0;
        return error * correctionCoefficient; //try to keep disc in center of screen
    }

    public void TurnTowardsVuMark(double speed, int vumarkCode, double correctionCoefficient, boolean useFrontVuforia){
        double[] data;
        data = vuforiaFront.GetData(vumarkCode);

        double rotationalError = data[4]; //get heading
        /*if(rotationalError > 0) rotationalError -= 180;
        else if(rotationalError < 0) rotationalError += 180;*/
        if(!(rotationalError > 0 ||rotationalError < 0 || rotationalError == 0)) rotationalError = 0;

        opMode.telemetry.addData("VuMark heading error ", rotationalError);

        if(!(rotationalError > 0 || rotationalError < 0 || rotationalError ==0)) return; //if its null

        rr.TurnRaw(speed*rotationalError*correctionCoefficient); //turn based on the rotational error towards vumark
    }

    public double TurnTowardsVuMarkSpeed(int vumarkCode, double correctionCoefficient, boolean useFrontVuforia){
        double[] data;
        if(useFrontVuforia) data = vuforiaFront.GetData(vumarkCode);
        else data = vuforiaBack.GetData(vumarkCode);

        double rotationalError = data[4]; //TODO: Figure out whether to use 4 or 5
        if(!(rotationalError > 0 || rotationalError < 0 || rotationalError ==0)) return 0; //if its null

        return rotationalError * correctionCoefficient; //turn based on the rotational error towards vumark
    }

    public void MoveRaw(double x, double y, double turn){rr.MoveRaw(new Pose2d(x,y,turn));}
    public void TurnRaw(double speed){rr.TurnRaw(speed);}
    public void TurnTo(double angle){rr.TurnTo(angle);}

    public int GetNumberOfDiscs(){return tf.ReturnNumberOfDiscsInSight();}

    //TODO: ====TELEMETRY METHODS FOR DEBUG====
    public void PrintVuforiaTelemetry(int vumarkCode){
        double[] data = vuforiaFront.GetData(vumarkCode);
        opMode.telemetry.addData("vumark is ",data[3] + " inches away, "+data[4]+" degrees right, and "+data[0]+" inches high.");
    }
    public void PrintTensorflowTelemetry(){
        //opMode.telemetry.addLine("===ALL TF OBJECTS===");
        //List<Recognition> tfObjs = tf.GetRecognitions();
        opMode.telemetry.addLine("===TF GetClosestDiscXYAngle() DATA===");
        double[] data = tf.GetClosestDiscXYAngleLocal(tfDistCoefficient,tfXCoefficient);
        //data = cs.ConvertToGlobalComplex(data[0], data[1], data[2]);
        opMode.telemetry.addLine("x: " + data[0] + " y: " + data[1] + " angle: " + data[2]);

        opMode.telemetry.addLine("===TF GetClosestDisc() DATA===");
        data = tf.GetClosestDisc();
        opMode.telemetry.addLine("xScreenPos: " + data[0] + "yScreenPos: " + data[1] + "width: " + data[2]);
    }
}
