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
        orion.SetPose(robotX, robotY, robotH);//robot starts on blue left line
        orion.MoveLinear(32,0,0);//move to disc stack
        sleep(500);//wait for tensorflow to detect discs
        int numberOfDiscs = orion.GetNumberOfDiscs();//figure out where to go

        if(numberOfDiscs == 0){ //go to A
            telemetry.addLine("route 1");
            orion.MoveSpline(30, 12, 0);//drop off wobble goal 1
        }

        else if(numberOfDiscs > 0 && numberOfDiscs < 3){ //go to B
            telemetry.addLine("route 2");
            orion.MoveSpline(30, -12, 0);//drop off wobble goal 1
            orion.MoveLinear(-20,-30, 0);
            orion.MoveLinear(-42,0, 0);
            orion.MoveLinear(0,16,0);
            orion.MoveLinear(30,0,0);
            orion.MoveSpline(32,10,0);

        }

        else{ //go to C
            telemetry.addLine("route 3");
        }
    }
}
