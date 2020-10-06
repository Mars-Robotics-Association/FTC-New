package org.firstinspires.ftc.teamcode.MechanicalControl;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake
{
    ////DEPENDENCIES////
    //Motors
    private DcMotor[] intakeMotors;

    public void Init(DcMotor[] setIntakeMotors){
        intakeMotors = setIntakeMotors;
    }

    public void SetIntakePower(double power){
        for (DcMotor motor: intakeMotors) {
            motor.setPower(power);
        }
    }
}
