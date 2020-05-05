package org.firstinspires.ftc.teamcode.Algorithm;

public class EncoderAutonomousAlgorithms extends DriveAlgorithm
{
    //REFERENCES
    MecanumDriveCalc MotorSpeedCalc;

    //VARIABLES


    //Initializer for class
    public EncoderAutonomousAlgorithms()
    {
        MotorSpeedCalc = new MecanumDriveCalc();
    }

    //Uses MecanumDriveCalc for raw wheel speed calculations
    @Override
    public double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset) {
        return MotorSpeedCalc.CalculateWheelSpeeds(angleToMoveAt, speed, turnOffset);
    }



    //A simple method that returns whether a value is close enough to the target
    public boolean IsCloseEnough(double target, double current, double threshold)
    {
        if(Math.abs(target - current) <= threshold)
        {
            return true;
        }
        else return false;
    }
}
