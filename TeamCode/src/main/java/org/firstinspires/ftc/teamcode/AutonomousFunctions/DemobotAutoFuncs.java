package org.firstinspires.ftc.teamcode.AutonomousFunctions;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Mechanical.DemobotChassis;
import org.firstinspires.ftc.teamcode.Odometry.DemoBotOdometry;
import org.firstinspires.ftc.teamcode.Core.IMU;

//The collection of autonomous routines used by the demobot.

//REQUIRED TO RUN: Phones | REV Hub | Demobot Chassis | Odometry Unit

public class DemobotAutoFuncs
{
    //Dependencies
    DemobotControl Control;
    DemobotChassis Chassis;
    DemoBotOdometry Odometry;
    IMU Imu;
    //Variables


    //Initializer
    public DemobotAutoFuncs(DemobotControl setControl)
    {
        Control = setControl;
        Chassis = Control.GetChassis();
        Odometry = Control.GetOdometry();
        Imu = Control.GetImu();
    }

    ////CALLABLE METHODS////
    //Movement
    public void MoveAtAngle(double angle, double distance, double speed){
        //Move robot at angle until it goes a certain distance using odometry

        //Reset everything
        Chassis.StopAndResetEncoders();
        Odometry.Reset();
        //Set the target distance to go
        Odometry.SetTargetDist(distance);
        //Loop while not close enough within the threshold
        while (!Odometry.IsAtTargetDist(1)) {//TODO: determine good threshold and scale with speed OR just wait till current > target
            //Move
            Chassis.MoveAtAngle(angle, speed, 0);
            //Return telemetry
            Control.GetOpMode().telemetry.addData("Target Dist: ", distance);
            Control.GetOpMode().telemetry.addData("Current Dist: ", Odometry.CalculateDistance());
        }
        Chassis.Brake();
    }

    public void SpotTurn(double angle, double speed, double rampVal){//TODO: Review
        while(Math.abs(Imu.GetRobotAngle()-angle)<10) {
            Chassis.SpotTurn(speed);
            Chassis.StopAndResetEncoders();
            Chassis.SetModeRunUsingEncoders();
        }
    }


    public void SweepTurn(double angle, double speed, double sweepVal){}
    public void GoToLine(double speed, double lightVal){}
    //Shooter/Intake
    public void Aim(){}
    public void Shoot(){}
    public void Collect(){}
    public void CollectAimShoot(){}

    ////PRIVATE METHODS////
    //Movement

    //Shooter/Intake


}
