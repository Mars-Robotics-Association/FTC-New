package org.firstinspires.ftc.teamcode.AutonomousFunctions;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.MechanicalControl.DemobotChassis;
import org.firstinspires.ftc.teamcode.Navigation.Odometry.DemoBotOdometry;
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
        //Odometry = Control.GetOdometry();
        Imu = Control.GetImu();
    }

    ////CALLABLE METHODS////
    //Movement
    /*public void MoveAtAngle(double angle, double distance, double speed){
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
    }*/

    public void MoveSpline(double x, double y, double tan){
        Control.GetOrion().MoveSpline(x,y,tan);
        //TODO: do we need to manually wait here?
    }

    public void RoadrunnerTurn(double angle){Control.GetOrion().Turn(angle);}

    public void SpotTurn(double angle, double baseSpeed, double rampVal){//TODO: Review
        //Reset chassis
        Chassis.StopAndResetEncoders();
        Chassis.SetModeRunUsingEncoders();
        //Loop while not within ten degrees of target
        while(Math.abs(Imu.GetRobotAngle()-angle)<10) {//TODO: add ramp calculation
            //Turn
            Chassis.SpotTurn(baseSpeed);
            //Return telemetry
            Control.GetOpMode().telemetry.addData("Target Angle: ", angle);
            Control.GetOpMode().telemetry.addData("Current Angle: ", Imu.GetRobotAngle());
        }
        //Stop and brake
        Chassis.StopAndResetEncoders();
        Chassis.Brake();
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

    /*////CALLABLE METHODS////
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

    public void SpotTurn(double angle, double baseSpeed, double rampVal){//TODO: Review
        //Reset chassis
        Chassis.StopAndResetEncoders();
        Chassis.SetModeRunUsingEncoders();
        //Loop while not within ten degrees of target
        while(Math.abs(Imu.GetRobotAngle()-angle)<10) {//TODO: add ramp calculation
            //Turn
            Chassis.SpotTurn(baseSpeed);
            //Return telemetry
            Control.GetOpMode().telemetry.addData("Target Angle: ", angle);
            Control.GetOpMode().telemetry.addData("Current Angle: ", Imu.GetRobotAngle());
        }
        //Stop and brake
        Chassis.StopAndResetEncoders();
        Chassis.Brake();
    }*/