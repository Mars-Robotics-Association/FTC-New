package org.firstinspires.ftc.teamcode.OpMode.Belinda;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Core.Robots.BelindaControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

@Config
@Autonomous(name = "*WOBBLE GOAL*", group = "Competition")
public class WobbleGoalAutonomous extends LinearOpMode {
    private BelindaControl control;
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
        control = new BelindaControl(this,true,false,true);
        control.Init();
        orion = control.GetOrion();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);

        waitForStart();
        orion.SetPose(robotX, robotY, robotH);//robot starts on blue left line
        orion.MoveLinear(28,0,0);//move to disc stack
        sleep(500);//wait for tensorflow to detect discs
        int numberOfDiscs = orion.GetNumberOfDiscs();//figure out where to go

        if(numberOfDiscs == 0){ //go to A
            telemetry.addLine("close target");
            orion.MoveSpline(30, 12, 0);//drop off wobble goal 1
            orion.MoveLinear(-62, -36, 0);//go to second wobble goal
            orion.MoveLinear(0, -24, 0);
            orion.MoveSpline(62, 60, 0);//places it
        }

        else if(numberOfDiscs > 0 && numberOfDiscs < 3){ //go to B
            telemetry.addLine("middle target");
            orion.MoveSpline(54, -2, 0);//drop off wobble goal 1
            /*orion.MoveLinear(-10, -30, 0);
            orion.MoveLinear(-72, 0, 0);*/
            orion.MoveLinear(-44,-50, 0);//heads back
            orion.MoveLinear(-40,0, 0);
            orion.MoveLinear(0,20,0);//lines up for second wobble goal
            orion.MoveLinear(30,-3,0);//places it
            orion.MoveSpline(56,10,0);
            orion.MoveLinear(-15,0,0);//goes back to line

        }

        else{ //go to C
            telemetry.addLine("far target");
            orion.MoveSpline(78, 12, 0);//drop off wobble goal 1
            orion.MoveLinear(-68,-52, 0);//heads back
            orion.MoveLinear(-42,0, 0);
            orion.MoveLinear(0,16,0);//lines up for second wobble goal
            orion.MoveLinear(30,0,0);//places it
            orion.MoveSpline(80,34,0);
        }
    }
}
