package org.firstinspires.ftc.teamcode.AutonomousFunctions;

//The collection of autonomous routines used by the demobot.

import org.firstinspires.ftc.teamcode.Core.DemobotControl;
import org.firstinspires.ftc.teamcode.Mechanical.DemobotChassis;
import org.firstinspires.ftc.teamcode.Odometry.DemoBotOdometry;

public class DemobotAutoFuncs
{
    //Dependencies
    DemobotControl Control;
    DemobotChassis Chassis;
    DemoBotOdometry Odometry;

    //Variables


    //Initializer
    public DemobotAutoFuncs(DemobotControl setControl)
    {
        Control = setControl;
        Chassis = Control.GetChassis();
        Odometry = Control.GetOdometry();
    }

    ////CALLABLE METHODS////
    //Movement
    public void MoveAtAngle(double angle, double distance, double speed){}
    public void SpotTurn(double angle, double speed, double rampVal){}
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
