package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;

//A simple autonomous script for the demobot

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Shooter | Odometry Unit

@Autonomous(name = "Demobot Autonomous")
public class DemobotAutonomous extends LinearOpMode {
    DemobotControl Control;


    @Override
    public void runOpMode() throws InterruptedException {
        //Init Control
        Control = new DemobotControl(this);
        Control.Init();
        Control.Start();

        //Forward test
        //Control.OdometryDrive(0, 0.4, 50);

        //Strafe test
        Control.OdometryDrive(90, 0.4, 50);

        //Angle test
        //Control.OdometryDrive(45, 0.4, 50);
    }
}
