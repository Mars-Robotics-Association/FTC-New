package org.firstinspires.ftc.teamcode.Navigation;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

@Config
@Autonomous(name = "NavigationTest")
public class NavigationTesting extends LinearOpMode {
    private DemobotControl control;
    private OrionNavigator orion;


    public void Init(){
        control = new DemobotControl(this,false,false,true);
        control.Init();

        orion = new OrionNavigator(this);
        orion.Init();
    }

    public void runOpMode(){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        orion.GetVuforia(cameraMonitorViewId);
    }
}
