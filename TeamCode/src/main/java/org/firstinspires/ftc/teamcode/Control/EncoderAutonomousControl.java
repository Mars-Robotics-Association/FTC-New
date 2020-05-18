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
    private int EncoderTicksForward = 1;
    private int EncoderTicksSideToSide = 1;
    private int EncodedDistance = 0;
    private int EncoderThreshold = 20;

    //Turning Vars
    private boolean IsTurning = false;
    private double TargetAngle = 0;
    private double TurnSpeed = 0;
    private double TurnThreshold = 5;
    private double InitialTurnDif = 0;
    private double TurnRampOffset = 0.1;


    //Initializer
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
        if(DriveCalc.IsCloseEnough(EncodedDistance, Motors[0].getCurrentPosition(), EncoderThreshold) && !IsTurning)
        {
            StopAndResetEncoders();
        }

        if(IsTurning)//If robot needs to turn
        {
            LoopTurn();
        }
    }

    //Loop code if we are moving
    private void LoopMove()
    {

    }

    //Loop code if we are turning
    private void LoopTurn()
    {
        double turnDif = TargetAngle - (IMURef.GetAngles().firstAngle - (HeadlessOffset + BaseOffset)); //get the difference in degrees between the current and target angles

        //turn right
        if(turnDif > 1) {
            DriveCalc.CalculateTurn(TurnSpeed * (turnDif / InitialTurnDif) + TurnRampOffset); //multiplies turn speed by the percent of remaining distance with an offset
        }

        //turn left
        if(turnDif < 1) {
            DriveCalc.CalculateTurn(TurnSpeed * (turnDif / InitialTurnDif) - TurnRampOffset); //multiplies turn speed by the percent of remaining distance with an offset
        }

        //if close enough, stop turning
        if(DriveCalc.IsCloseEnough(TargetAngle, IMURef.GetAngles().firstAngle - (HeadlessOffset + BaseOffset), TurnThreshold)){
            IsTurning = false;
        }
    }


    //Move forward and backwards using encoders
    public void EncoderMoveY(double distance, double speed)
    {
        StopAndResetEncoders();
        EncodedDistance = (int)((EncoderTicksForward)*distance);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)
        int[] motorDists = DriveCalc.CalculateForwards(EncodedDistance);//get an array of distances for the motors to go

        //Sets motor target dists to array
        int i = 0;
        for (DcMotor m:Motors) {
            m.setTargetPosition(motorDists[i]);
            i ++;
        }

        SetMotorSpeeds(speed);
        SetRunToPosition();
    }

    //Move side to side using encoders
    public void EncoderMoveX(double distance, double speed)
    {
        StopAndResetEncoders();
        EncodedDistance = (int)((EncoderTicksSideToSide)*distance);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)
        int[] motorDists = DriveCalc.CalculateSideways(EncodedDistance);//get an array of distances for the motors to go

        //Sets motor target dists to array
        int i = 0;
        for (DcMotor m:Motors) {
            m.setTargetPosition(motorDists[i]);
            i ++;
        }

        SetMotorSpeeds(speed);
        SetRunToPosition();
    }

    //Turn with a ramp applied (so it slows down as it gets closer
    public void RampTurn(double degrees, double speed)
    {
        IsTurning = true;
        TargetAngle = degrees;
        TurnSpeed = speed;
        InitialTurnDif = TargetAngle - (IMURef.GetAngles().firstAngle - (HeadlessOffset + BaseOffset));
    }

    //Set the speed of all motors
    public void SetMotorSpeeds(double speed)
    {
        for (DcMotor m:Motors) {
            m.setPower(speed);
        }
    }

    //enable run to position on all motors
    public void SetRunToPosition()
    {
        for (DcMotor m:Motors) {
            m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    //Stop and reset all motors
    public void StopAndResetEncoders()
    {
        EncodedDistance = 0;
        for (DcMotor m:Motors) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setTargetPosition(0);
        }
    }
}
