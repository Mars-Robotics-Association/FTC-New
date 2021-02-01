package org.firstinspires.ftc.teamcode.OpMode.TestingOpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Core.Robots.BelindaControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

@Config
@Autonomous(name = "NavigationTest")
public class NavigationTesting extends LinearOpMode {
    private BelindaControl control;
    private OrionNavigator orion;
    private FtcDashboard dashboard;

    public static double tfDistCoef = 6666;
    public static double tfXCoef = 0.001;

    public static double robotX = 0;
    public static double robotY = 0;
    public static double robotH = 0;

    public static double discMoveCoefficient = -0.0015;
    public static double discMoveSpeed = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        control = new BelindaControl(this,true,false,true);
        control.Init();
        orion = control.GetOrion();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);

        waitForStart();
        orion.SetPose(robotX, robotY, robotH);

        while (!isStopRequested()){
            control.TurnTowardsVuMark();
        }
    }

    /*@Override
    public void loop(){
        orion.SetTFCoefficients(tfDistCoef, tfXCoef);
        //orion.PrintVuforiaTelemetry(2);
        //orion.GoToDisc();
        orion.PrintTensorflowTelemetry();

        //orion.MoveToVumark(2, 0, 0, 0,5, 5);

        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();
        //packet.put("target X ", control.GetImu().GetAngularVelocity());
        dashboard.sendTelemetryPacket(packet);
    }*/
}
