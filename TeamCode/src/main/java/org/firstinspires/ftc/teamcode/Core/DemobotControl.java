package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.teamcode.Attachments.DemoArcShooter;
import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemoChassisAutoFuncs;
import org.firstinspires.ftc.teamcode.AutonomousFunctions.DemoShooterAutoFuncs;
import org.firstinspires.ftc.teamcode.Odometry.DemoBotOdometry;

public class DemobotControl
{
    //Dependencies
    private DemoBotOdometry Odometry;
    private IMU Imu;
    private DemoArcShooter Shooter;
    private DemoChassisAutoFuncs ChassisAutoFuncs;
    private DemoShooterAutoFuncs ShooterAutoFuncs;

    //Variables


    //Initializer
    public DemobotControl()
    {

    }

    //Methods
    private void RawDrive() {}
    private void OdometryDrive() {}
    private void SpotTurn() {}
    private void SweepTurn() {}
    private void ShooterGoBoom() {}
    private void AimShooter() {}
    private void Intake() {}
}
