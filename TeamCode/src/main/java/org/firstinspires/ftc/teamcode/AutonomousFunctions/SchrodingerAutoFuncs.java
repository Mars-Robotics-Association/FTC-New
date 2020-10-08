package org.firstinspires.ftc.teamcode.AutonomousFunctions;

import org.firstinspires.ftc.robotcore.internal.tfod.Timer;
import org.firstinspires.ftc.teamcode.Core.SchrodingerControl;
import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerArm;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerGripper;

public class SchrodingerAutoFuncs
{
    private SchrodingerControl control;

    private double targetTime = 0;
    private Timer timer;

    public SchrodingerAutoFuncs(SchrodingerControl setControl){
        control = setControl;
    }

    public void Dance() throws InterruptedException {
        //TODO: make robot dance
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
}
