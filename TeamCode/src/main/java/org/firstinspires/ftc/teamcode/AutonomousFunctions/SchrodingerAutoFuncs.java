package org.firstinspires.ftc.teamcode.AutonomousFunctions;

import org.firstinspires.ftc.teamcode.MechanicalControl.Intake;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerArm;
import org.firstinspires.ftc.teamcode.MechanicalControl.Schrodinger.SchrodingerGripper;

public class SchrodingerAutoFuncs
{
    SchrodingerArm arm;
    SchrodingerGripper gripper;
    Intake intake;

    public SchrodingerAutoFuncs(SchrodingerArm setArm, SchrodingerGripper setGripper, Intake setIntake){
        arm = setArm;
        gripper = setGripper;
        intake = setIntake;
    }

    public void Dance(){
        //TODO: make robot dance
    }
}
