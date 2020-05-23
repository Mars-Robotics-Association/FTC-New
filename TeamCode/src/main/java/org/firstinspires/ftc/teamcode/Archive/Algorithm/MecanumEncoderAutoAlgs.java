package org.firstinspires.ftc.teamcode.Archive.Algorithm;

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
        int[] out = new int[4];
        out[0] = distance;
        out[1] = distance;
        out[2] = -distance;
        out[3] = -distance;
        return new int[0];
    }

    @Override
    public double[] CalculateTurn(double speed) {
        double[] out = new double[4];
        out[0] = speed;
        out[1] = speed;
        out[2] = speed;
        out[3] = speed;
        return new double[0];
    }

    public double CalculateRelativeAngle(double degrees, double robotAngle)
    {
        return (degrees - robotAngle);
    }


    //A simple method that returns whether a value is close enough to the target
    @Override
    public boolean IsCloseEnough(double target, double current, double threshold)
    {
        if(Math.abs(target - current) <= threshold)
        {
            return true;
        }
        else return false;
    }
}
