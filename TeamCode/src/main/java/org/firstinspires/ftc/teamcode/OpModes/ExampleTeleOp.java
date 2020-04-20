package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Algorithm.MecanumDriveCalc;

public class ExampleTeleOp extends TeleOpMode
{
    //REFERENCES

    //VARIABLES
    //-Motor Names-
    //drive motors
    String FR = "FR";
    String FL = "FL";
    String RR = "RR";
    String RL = "RL";

    @Override
    public void init() {
        DriveCalc = new MecanumDriveCalc();
        BaseSpeed = 1;
        BaseOffset = 0;
        Motors = new DcMotor[4];//Set Motors
        Motors[0] = hardwareMap.dcMotor.get(FR);
        Motors[1] = hardwareMap.dcMotor.get(FL);
        Motors[2] = hardwareMap.dcMotor.get(RR);
        Motors[3] = hardwareMap.dcMotor.get(RL);

        InitTeleOpControl();
    }

    @Override
    public void start() {
        StartTeleOpControl();
    }

    @Override
    public void loop() {
        RobotChassisControl.SetMoveAngle(0, true);
        LoopTeleOpControl();
    }

    @Override
    public void A1Pressed() {

    }

    @Override
    public void B1Pressed() {

    }

    @Override
    public void X1Pressed() {
        RobotChassisControl.ResetHeadlessOffset();
    }

    @Override
    public void Y1Pressed() {

    }

    @Override
    public void A1Released() {

    }

    @Override
    public void B1Released() {

    }

    @Override
    public void X1Released() {

    }

    @Override
    public void Y1Released() {

    }

    @Override
    public void LB1Pressed() {

    }

    @Override
    public void RB1Pressed() {

    }

    @Override
    public void LT1Pressed() {

    }

    @Override
    public void RT1Pressed() {

    }

    @Override
    public void LB1Released() {

    }

    @Override
    public void RB1Released() {

    }

    @Override
    public void LT1Released() {

    }

    @Override
    public void RT1Released() {

    }

    @Override
    public void A2Pressed() {

    }

    @Override
    public void B2Pressed() {

    }

    @Override
    public void X2Pressed() {

    }

    @Override
    public void Y2Pressed() {

    }

    @Override
    public void A2Released() {

    }

    @Override
    public void B2Released() {

    }

    @Override
    public void X2Released() {

    }

    @Override
    public void Y2Released() {

    }

    @Override
    public void LB2Pressed() {

    }

    @Override
    public void RB2Pressed() {

    }

    @Override
    public void LT2Pressed() {

    }

    @Override
    public void RT2Pressed() {

    }

    @Override
    public void LB2Released() {

    }

    @Override
    public void RB2Released() {

    }

    @Override
    public void LT2Released() {

    }

    @Override
    public void RT2Released() {

    }
}
