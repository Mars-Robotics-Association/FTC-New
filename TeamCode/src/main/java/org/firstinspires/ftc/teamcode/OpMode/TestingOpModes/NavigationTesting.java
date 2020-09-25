package org.firstinspires.ftc.teamcode.OpMode.TestingOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;
import org.firstinspires.ftc.teamcode.Navigation.Vuforia.VuMarkNavigation;

@Config
@Autonomous(name = "NavigationTest")
public class NavigationTesting extends OpMode {
    private DemobotControl control;
    private OrionNavigator orion;

    @Override
    public void init(){
        control = new DemobotControl(this,false,false,true);
        control.Init();
    }

    @Override
    public void loop(){
        control.GetOrion().GetVuforia(0);
        telemetry.update();
    }
}
