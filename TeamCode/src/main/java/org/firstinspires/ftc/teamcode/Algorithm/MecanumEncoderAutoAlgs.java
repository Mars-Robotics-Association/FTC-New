package org.firstinspires.ftc.teamcode.Algorithm;

public class MecanumEncoderAutoAlgs extends AutonomousDriveAlgorithm
{
    //REFERENCES
    MecanumDriveCalc MotorSpeedCalc;

    //VARIABLES


    //Initializer for class
    public MecanumEncoderAutoAlgs()
    {
        MotorSpeedCalc = new MecanumDriveCalc();
    }

    //Uses MecanumDriveCalc for raw wheel speed calculations
    @Override
    public double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset) {
        return MotorSpeedCalc.CalculateWheelSpeeds(angleToMoveAt, speed, turnOffset);
    }

    @Override
    public int[] CalculateForwards(int distance) {
        int[] out = new int[4];
        out[0] = distance;
        out[1] = -distance;
        out[2] = distance;
        out[3] = -distance;
        return new int[0];
    }

    @Override
    public int[] CalculateSideways(int distance) {
        return new int[0];
    }

    @Override
    public double[] CalculateTurn(double degrees) {
        return new double[0];
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
