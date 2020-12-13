package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

@Config
@Autonomous(name = "*WOBBLE GOAL*")
public class WobbleGoalAutonomous extends LinearOpMode {
    private DemobotControl control;
    private OrionNavigator orion;
    private FtcDashboard dashboard;

    public static double tfDistCoef = 6666;
    public static double tfXCoef = 0.001;

    public static double robotX = 0;
    public static double robotY = 0;
    public static double robotH = 0;

    public static double robotMoveX = 0;
    public static double robotMoveY = 0;
    public static double robotMoveTan = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        control = new DemobotControl(this,true,false,true);
        control.Init();
        orion = control.GetOrion();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);

        waitForStart();
        orion.SetPose(robotX, robotY, robotH);
        orion.MoveLinear(32,0,0);
        sleep(500);
        int numberOfDiscs = orion.GetNumberOfDiscs();        //figure out where to go
        if(numberOfDiscs == 0){
            telemetry.addLine("route 1");
            orion.MoveSpline(30, 12, 0);
        }
        else if(numberOfDiscs > 0 && numberOfDiscs < 3){
            telemetry.addLine("route 2");
            orion.MoveSpline(30, -12, 0); 
        }
        else{
            telemetry.addLine("route 3");
        }
    }
}
