package org.firstinspires.ftc.teamcode.MechanicalControl;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class SchrodingerArm
{
    ////DEPENDENCIES////
    //Motors
    private DcMotor armMotor, intakeMotorR, intakeMotorL;
    //Servos
    private Servo armServoR, armServoL;

    ////VARIABLES////

    //Arm Rotation
    //TODO- Find max and min of arm in degrees with intake as 0
    public static double armRotationMax = 180;
    public static double armRotationMin = 0;
    private double targetRotation;

    //Arm Extension
    //TODO- Find max and min of arm extension
    public static double armExtensionMax = 10;
    public static double armExtensionMin = 0;
    private double targetExtension;

    public void Init(DcMotor setArmMotor, DcMotor setIntakeMotorR, DcMotor setIntakeMotorL, Servo setArmServoR, Servo setArmServoL){
        armMotor = setArmMotor;
        intakeMotorR = setIntakeMotorR;
        intakeMotorL = setIntakeMotorL;
        armServoR = setArmServoR;
        armServoL = setArmServoL;
    }

    public void SetTargetRotation(double rotation){ }
    public void SetTargetExtension(double inches){ }
    public void ChangeRotation(double speed){ }
    public void ChangeExtension(double speed){ }
    public void ResetArm(){ }
}
