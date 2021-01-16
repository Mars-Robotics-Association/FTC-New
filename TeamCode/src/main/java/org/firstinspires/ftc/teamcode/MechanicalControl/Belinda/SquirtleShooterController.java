package org.firstinspires.ftc.teamcode.MechanicalControl.Belinda;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class SquirtleShooterController
{
    ////DEPENDENCIES////
    //Motors
    private DcMotor[] shooterMotors;
    private double[] motorSpeedMultipliers;
    private Servo[] servos;
    private double servoSpeedMultiplier;

    public void Init(DcMotor[] setShooterMotors, double[] setSpeedMultipliers, Servo[] setServos, double setServoSpeedMultiplier){
        shooterMotors = setShooterMotors;
        motorSpeedMultipliers = setSpeedMultipliers;
        servos = setServos;
        servoSpeedMultiplier = setServoSpeedMultiplier;
    }

    public void SetShooterPower(double power){
        int i = 0;
        for (DcMotor motor: shooterMotors) {
            motor.setPower(power * motorSpeedMultipliers[i]);
            i++;
        }
    }

    public void SetIntakePower(double power){
        for (Servo s : servos) {
            s.setPosition(power * servoSpeedMultiplier);
        }
    }

    public void ShooterOn(){SetShooterPower(1);}
    public void ShooterOff(){SetShooterPower(0);}
    public void IntakeOn(){SetIntakePower(1);}
    public void IntakeOff(){SetIntakePower(0);}
}
