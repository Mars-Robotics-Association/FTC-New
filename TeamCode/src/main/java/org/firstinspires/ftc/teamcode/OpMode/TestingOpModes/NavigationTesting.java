package org.firstinspires.ftc.teamcode.OpMode.TestingOpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

@Config
@Autonomous(name = "NavigationTest")
public class NavigationTesting extends OpMode {
    private DemobotControl control;
    private OrionNavigator orion;
    private FtcDashboard dashboard;

    public static double tfDistCoef = 6666;
    public static double tfXCoef = 1;

    public static double robotX = 0;
    public static double robotY = 0;
    public static double robotH = 0;

    @Override
    public void init(){
        control = new DemobotControl(this,false,false,true);
        control.Init();
        orion = control.GetOrion();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
    }

    @Override
    public void start(){
        orion.SetPose(0,0,0); //TODO: Set this to the robot's pose at start in global coordinates
    }

    @Override
    public void loop(){
        orion.SetTFCoefficients(tfDistCoef, tfXCoef);
        orion.PrintVuforiaTelemetry(2);
        //orion.PrintTensorflowTelemetry();

        orion.SetPose(robotX, robotY, robotH);
        orion.MoveToVumark(2, 0, 0, 0,5, 5);

        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();
        //packet.put("target X ", control.GetImu().GetAngularVelocity());
        dashboard.sendTelemetryPacket(packet);
    }
}
