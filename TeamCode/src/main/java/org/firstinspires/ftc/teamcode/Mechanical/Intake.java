package org.firstinspires.ftc.teamcode.Mechanical;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake
{
    ////DEPENDENCIES////
    //Motors
    private DcMotor intakeMotor;

    public void Init(DcMotor setIntakeMotor){
        intakeMotor = setIntakeMotor;
    }

    public void IntakeOn(){
        intakeMotor.setPower(1);
    }
    public void IntakeOff(){
        intakeMotor.setPower(0);
    }
}
