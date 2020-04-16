package org.firstinspires.ftc.teamcode;

public class CB2_MecanumDriveCalcTutorial extends C_Algorithm
{
    public double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset)
    {
        double FR = 0;
        double FL = 0;
        double RR = 0;
        double RL = 0;

        FR = -Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;
        FL = Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RR = -Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RL = Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;

        //Covert to an array 4 doubles
        double[] out = new double[4];
        out[0] = FR;
        out[1] = FL;
        out[2] = RR;
        out[3] = RL;

        return out;

    }

    double[] vals = CalculateWheelSpeeds(0,1, 0);

}
