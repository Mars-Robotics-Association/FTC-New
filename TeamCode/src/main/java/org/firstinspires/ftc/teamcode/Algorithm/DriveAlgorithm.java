package org.firstinspires.ftc.teamcode.Algorithm;

public abstract class DriveAlgorithm extends Algorithm
{
    public abstract double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset);
}