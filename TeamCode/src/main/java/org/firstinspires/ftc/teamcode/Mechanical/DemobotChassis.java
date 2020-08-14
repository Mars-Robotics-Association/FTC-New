package org.firstinspires.ftc.teamcode.Mechanical;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.IMU;
import org.firstinspires.ftc.teamcode.Core.PID;

//Class for controlling the chassis of the demobot. Includes basic turning and driving. NOTE: the
//turn and move stuff here has to be called continuously (every loop), or it won't function properly.

//REQUIRED TO COMPILE: Phones | REV Hub
//REQUIRED TO RUN: Chassis

public class DemobotChassis
{
    ////Dependencies////
    private OpMode CurrentOpMode;
    private PID PidController;
    private IMU Imu;

    ////Variables////
    //Motors
    private DcMotor FR;
    private DcMotor FL;
    private DcMotor RR;
    private DcMotor RL;
    //Brake pos
    private int FRBrakePos = 0;
    private int FLBrakePos = 0;
    private int RRBrakePos = 0;
    private int RLBrakePos = 0;
    //pid movement

    //telemetry
    private Telemetry RobotTelemetry;

    //Initializer
    public DemobotChassis(IMU setImu, DcMotor fr, DcMotor fl, DcMotor rr, DcMotor rl, Telemetry telemetry){
        Imu = setImu;
        FR = fr;
        FL = fl;
        RR = rr;
        RL = rl;
        RobotTelemetry = telemetry;
    }

    ////STARTUP////
    public void Init(){
        SetMotorSpeeds(0,0,0,0);
        StopAndResetEncoders();
        SetModeRunWithoutEncoders();

        PidController = new PID(0.0001,0.0001,0.0001);//Create the pid controller. TODO: figure out good (p,i,d) constants
    }

    ////CALLABLE METHODS////
    //Movement
    public void MoveAtAngle(double angle, double speed, double turnSpeed){
        //Tells robot to raw move at any angle. Turn speed variable causes it to sweep turn / corkscrew.
        //This is called continuously while the robot is driving.

        //Sets the mode so that robot can drive and record encoder values
        SetModeRunWithoutEncoders();

        //Gets speeds for the motors
        double[] speeds = CalculateWheelSpeedsTurning(angle, speed, turnSpeed);

        //Uses pid controller to correct for error using (currentAngle, targetAngle) TODO: get pid working
        double pidOffset = PidController.getOutput(turnSpeed, Imu.GetAngularVelocity());
        RobotTelemetry.addData("Angular Velocity ", Imu.GetAngularVelocity());
        RobotTelemetry.addData("PID Offset ", pidOffset);

        //set the powers of the motors with pid offset applied
        SetMotorSpeeds(speeds[0]+pidOffset, speeds[1]+pidOffset, speeds[2]+pidOffset, speeds[3]+pidOffset);
        //Returns speed telemetry
        RobotTelemetry.addData("Speed FR ", speeds[0]+pidOffset);
        RobotTelemetry.addData("Speed FL ", speeds[1]+pidOffset);
        RobotTelemetry.addData("Speed RR ", speeds[2]+pidOffset);
        RobotTelemetry.addData("Speed RL ", speeds[3]+pidOffset);
        RobotTelemetry.update();

        //Updates brake pos, as this is called continuously as robot is driving
        UpdateBrakePos();
    }
    public void SpotTurn(double speed)
    {
        //Turns the robot on the spot. Must be called continuously to work.
        // Positive speed turns left, negative right.

        //Use motors and record encoder values
        SetModeRunWithoutEncoders();

        //Set motor speeds all equal, as this causes it to do a spot turn
        SetMotorSpeeds(speed, speed, speed, speed);

        //Update the values for breaking
        UpdateBrakePos();
    }

    //Utility
    public void StopAndResetEncoders(){
        //Stops motors and resets encoders to 0
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void SetModeRunUsingEncoders(){
        //Sets motors to try and hold a velocity
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void SetModeRunWithoutEncoders(){
        //Sets motors to run like dc motors but record the encoder values
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void SetModeGoToEncoderPos(){
        //Tells motors to move to the target encoder values set in SetTargetEncoderPos()
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void SetTargetEncoderPos(int FRPos, int FLPos, int RRPos, int RLPos){
        //Sets the target encoder values on the drive motors. Motors will try to go there when SetModeGoToEncoderPos() is called
        FR.setTargetPosition(FRPos);
        FL.setTargetPosition(FLPos);
        RR.setTargetPosition(RRPos);
        RL.setTargetPosition(RLPos);
    }
    public void SetMotorSpeeds(double FRSpeed, double FLSpeed, double RRSpeed, double RLSpeed){
        //Sets the speeds of the motors
        FR.setPower(FRSpeed);
        FL.setPower(FLSpeed);
        RR.setPower(RRSpeed);
        RL.setPower(RLSpeed);
    }
    public void UpdateBrakePos(){
        //Update the values for breaking
        FRBrakePos = FR.getCurrentPosition();
        FLBrakePos = FL.getCurrentPosition();
        RRBrakePos = RR.getCurrentPosition();
        RLBrakePos = RL.getCurrentPosition();
    }
    public void Brake(){
        //Stop the robot and hold position. Meant to be called once
        UpdateBrakePos();
        SetTargetEncoderPos(FRBrakePos, FLBrakePos, RRBrakePos, RLBrakePos);
        SetModeGoToEncoderPos();
        SetMotorSpeeds(0.5,0.5,0.5,0.5);
    }
    public void SetPIDCoefficients(double p, double i, double d){
        PidController.setPID(p,i,d);
    }

    public double[] CalculateWheelSpeedsTurning(double degrees, double speed, double turnSpeed)
    {
        //Returns the speeds the motors need to move at to move. A negative turn speed turns right, a positive left.

        /*Wheel speed is calculated by getting the cosine wave (which we found matches the speed that
         * the wheels need to go) with a positive 45 or negative 45 shift, depending on the wheel. This works
         * so that no matter the degrees, it will always come out with the right value. A turn offset is added
         * to the end for corkscrewing, or turning while driving*/
        double FRP = -Math.cos(Math.toRadians(degrees + 45)) * speed + turnSpeed;
        double FLP = Math.cos(Math.toRadians(degrees - 45)) * speed + turnSpeed;
        double RRP = -Math.cos(Math.toRadians(degrees - 45)) * speed + turnSpeed;
        double RLP = Math.cos(Math.toRadians(degrees + 45)) * speed + turnSpeed;

        double[] vals = {FRP, FLP, RRP, RLP};
        return vals;
    }

}
