package org.firstinspires.ftc.teamcode.Archive.Algorithm;

public abstract class AutonomousDriveAlgorithm extends DriveAlgorithm
{
    //REFERENCES

    //VARIABLES

    @Override
    public abstract double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset);

    public abstract int[] CalculateForwards(int distance);
    public abstract int[] CalculateSideways(int distance);
    public abstract double[] CalculateTurn(double speed);
    public abstract boolean IsCloseEnough(double target, double current, double threshold);

}
