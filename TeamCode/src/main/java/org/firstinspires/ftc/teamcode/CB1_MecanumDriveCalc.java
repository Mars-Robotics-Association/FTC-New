package org.firstinspires.ftc.teamcode;

public class CB1_MecanumDriveCalc extends CB_DriveAlgorithms
{
    //Calculates the necassary speeds and directions for the wheels to move at when going any angle. Also allows for sweeps turns/corkscrews
    public double[] CalculateWheelSpeedsTurning(double angleToMoveAt, double speed, double turnOffset) //TODO Separate out corkscrew and have it controllable
    {
        double FrontRightPower = 0;
        double FrontLeftPower = 0;
        double RearRightPower = 0;
        double RearLeftPower = 0;

        /*Wheel speed is calculated by getting the cosine wave (which we found matches the speed that
         * the wheels need to go) with a positive 45 or negative 45 shift, depending on the wheel. This works
         * so that no matter the degrees, it will always come out with the right value. A turn offset is added
         * to the end for corkscrewing, or turning while driving*/
        FrontRightPower = -Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;
        FrontLeftPower = Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RearRightPower = -Math.cos(Math.toRadians(angleToMoveAt - 45)) * speed + turnOffset;
        RearLeftPower = Math.cos(Math.toRadians(angleToMoveAt + 45)) * speed + turnOffset;

        //Converts to an array of 4 doubles
        double[] out = new double[4];
        out[0] = FrontRightPower;
        out[1] = FrontLeftPower;
        out[2] = RearRightPower;
        out[3] = RearLeftPower;

        return out;
    }
}
