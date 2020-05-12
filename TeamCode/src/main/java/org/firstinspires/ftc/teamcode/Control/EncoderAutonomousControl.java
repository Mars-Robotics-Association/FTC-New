package org.firstinspires.ftc.teamcode.Control;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Algorithm.AutonomousDriveAlgorithm;
import org.firstinspires.ftc.teamcode.Algorithm.DriveAlgorithm;
import org.firstinspires.ftc.teamcode.Algorithm.MecanumEncoderAutoAlgs;
import org.firstinspires.ftc.teamcode.Sensor.IMU;

public class EncoderAutonomousControl extends AutonomousControl
{
    //References
    OpMode opMode;
    AutonomousDriveAlgorithm DriveCalc;
    IMU IMURef;

    //Variables
    private double BaseOffset = 0;
    private double HeadlessOffset = 0;
    private double MoveAngle = 0;
    private boolean IsHeadless = true;

    private double BaseSpeed = 1;
    private double CurrentSpeed = 0;

    private double TurnOffset = 0;

    private DcMotor[] Motors;
    private double[] MotorSpeeds;

    //Encoder Vars
    double EncoderTicksForward = 1;
    double EncoderTicksSideToSide = 1;
    int EncodedDistance = 0;

    public EncoderAutonomousControl(OpMode setOpMode, DcMotor[] motors)
    {
        opMode = setOpMode;
        Motors = motors;
    }

    @Override
    public void Init() {
        DriveCalc = new MecanumEncoderAutoAlgs();
    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {

    }

    public void EncoderMoveY(double distance, double speed)
    {
        StopAndResetEncoders();
        EncodedDistance = (int)((EncoderTicksForward)*distance);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)
        int[] motorDists = DriveCalc.CalculateForwards(EncodedDistance);
        
        int i = 0;
        for (DcMotor m:Motors) {
            m.setTargetPosition(motorDists[i]);
            i ++;
        }

        SetMotorSpeeds(speed);
        SetRunToPosition();
    }

    public void EncoderMoveX(double distance, double speed)
    {

    }

    public void RampTurn(double degrees, double speed)
    {

    }

    public void SetMotorSpeeds(double speed)
    {
        for (DcMotor m:Motors) {
            m.setPower(speed);
        }
    }

    public void SetRunToPosition()
    {
        for (DcMotor m:Motors) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void StopAndResetEncoders()
    {
        for (DcMotor m:Motors) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }
}
