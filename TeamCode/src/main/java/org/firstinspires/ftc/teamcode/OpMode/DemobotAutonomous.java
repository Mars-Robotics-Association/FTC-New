package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;

//A simple autonomous script for the demobot

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

@Config
@Autonomous(name = "Demobot Autonomous")
public class DemobotAutonomous extends LinearOpMode {
    DemobotControl Control;

    //Pid coefficients
    public static double p;
    public static double i;
    public static double d;


    @Override
    public void runOpMode() throws InterruptedException {
        //Init Control
        Control = new DemobotControl(this, true, false, true);
        Control.Init();
        Control.Start();

        waitForStart();

        if (isStopRequested()) return;

        while (true){
            /*Control.SetDrivePID(p,i,d);
            Control.RawDrive(0,0,0);*/
            Control.FireShooter();
        }
    }
}
