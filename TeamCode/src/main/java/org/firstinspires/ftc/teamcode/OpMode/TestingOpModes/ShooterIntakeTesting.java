package org.firstinspires.ftc.teamcode.OpMode.TestingOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.DemobotControl;

@TeleOp(name = "ShooterIntakeTesting")
public class ShooterIntakeTesting extends OpMode
{
    private Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("IM");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            servo.setPosition(0.5);
        }
        else servo.setPosition(0);
    }
}
