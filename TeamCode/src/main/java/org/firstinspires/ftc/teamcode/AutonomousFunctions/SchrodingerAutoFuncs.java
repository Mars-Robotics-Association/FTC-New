package org.firstinspires.ftc.teamcode.AutonomousFunctions;

import org.firstinspires.ftc.robotcore.internal.tfod.Timer;
import org.firstinspires.ftc.teamcode.Core.SchrodingerControl;
import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerArm;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerGripper;
import org.firstinspires.ftc.teamcode.Navigation.Odometry.DemoBotOdometry;

public class SchrodingerAutoFuncs
{
    private SchrodingerControl control;
    private DemoBotOdometry odometry;

    private double targetTime = 0;
    private Timer timer;

    public SchrodingerAutoFuncs(SchrodingerControl setControl){
        control = setControl;
        odometry = control.GetOdometry();
    }

    public void Dance1() throws InterruptedException {
        control.RawTurn(1);
        Thread.sleep(100);
        control.RawTurn(-1);
        control.SwitchFoundationGrabberState();
        Thread.sleep(500);
        control.RawTurn(1);
        control.SwitchFoundationGrabberState();
        Thread.sleep(500);
        control.RawTurn(-1);
        control.SwitchFoundationGrabberState();
        Thread.sleep(500);
        control.ArmToPlace(1);
    }
    public void Dance2() throws InterruptedException {
        MoveAtAngle(0, 10, 1);
        control.SwitchFoundationGrabberState();
        Thread.sleep(500);
        MoveAtAngle(180, 10, 1);
        control.GetChassis().StopAndResetEncoders();
        control.SwitchFoundationGrabberState();
        Thread.sleep(500);
        control.RawTurn(1);
        Thread.sleep(1000);
        control.RawTurn(-1);
        Thread.sleep(1000);
        control.GetFoundationGrabbers().ExtendTape();
        Thread.sleep(500);
        control.GetFoundationGrabbers().RetractTape();
        Thread.sleep(500);
        control.GetFoundationGrabbers().StopTape();
    }

    //Movement
    public void MoveAtAngle(double angle, double distance, double speed){
        //Move robot at angle until it goes a certain distance using odometry

        //Reset everything
        control.GetChassis().StopAndResetEncoders();
        odometry.Reset();
        //Set the target distance to go
        odometry.SetTargetDist(distance);
        //Loop while not close enough within the threshold
        while (!odometry.IsAtTargetDist(1)) {//TODO: determine good threshold and scale with speed OR just wait till current > target
            //Move
            control.GetChassis().MoveAtAngle(angle, speed, 0);
            //Return telemetry
            control.GetOpMode().telemetry.addData("Target Dist: ", distance);
            control.GetOpMode().telemetry.addData("Current Dist: ", odometry.CalculateDistance());
        }
        control.GetChassis().Brake();
    }
}
