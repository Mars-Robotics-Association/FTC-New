package org.firstinspires.ftc.teamcode.OpMode.TestingOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Navigation.OrionNavigator;

@Config
@Autonomous(name = "NavigationTest")
public class NavigationTesting extends OpMode {
    private DemobotControl control;
    private OrionNavigator orion;

    public static double tfDistCoef = 1;
    public static double tfXCoef = 1;

    @Override
    public void init(){
        control = new DemobotControl(this,false,false,true);
        control.Init();
        orion = control.GetOrion();
    }

    @Override
    public void start(){
        orion.SetPose(0,0,0); //TODO: Set this to the robot's pose at start in global coordinates
    }

    @Override
    public void loop(){
        orion.SetTFCoefficients(tfDistCoef, tfXCoef);
        orion.PrintVuforiaTelemetry(2);
        orion.PrintTensorflowTelemetry();
        telemetry.update();
    }
}
