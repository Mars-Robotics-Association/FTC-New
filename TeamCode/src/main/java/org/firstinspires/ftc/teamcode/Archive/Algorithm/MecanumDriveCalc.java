package org.firstinspires.ftc.teamcode.Archive.Algorithm;

public class MecanumDriveCalc extends DriveAlgorithm//test
{
    //Calculates the necessary speeds and directions for the wheels to move at when going any angle. Also allows for sweeps turns/corkscrews
    @Override
    public double[] CalculateWheelSpeeds(double angleToMoveAt, double speed, double turnOffset)
    {
        double FR = 0;
        double FL = 0;
        double RR = 0;
        double RL = 0;

        /*Wheel speed is calculated by getting the cosine wave (which we found matches the speed that
         * the wheels need to go) with a positive 45 or negative 45 shift, depending on the wheel. This works
         * so that no matter the degrees, it will always come out with the right value. A turn offset is added
         * to the end for corkscrewing, or turning while driving*/
        FR = -Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;
        FL = Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RR = -Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RL = Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;

        //Covert to an array 4 doubles
        double[] out = new double[4];//
        out[0] = FR;
        out[1] = FL;
        out[2] = RR;
        out[3] = RL;

        return out;
    }
}
