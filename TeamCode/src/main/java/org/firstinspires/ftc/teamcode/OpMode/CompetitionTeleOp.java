package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.Input.ControllerInput;
import org.firstinspires.ftc.teamcode.Core.Input.ControllerInputListener;
import org.firstinspires.ftc.teamcode.Core.Robots.CuriosityUltimateGoalControl;

@TeleOp(name = "*COMPETITION TELEOP*", group = "Competition")
@Config
public class CompetitionTeleOp extends OpMode implements ControllerInputListener
{
    ////Dependencies////
    private CuriosityUltimateGoalControl control;
    private ControllerInput controllerInput1;
    private ControllerInput controllerInput2;

    ////Variables////
    //Tweaking Vars
    public static double driveSpeed = 1;//used to change how fast robot drives
    public static double turnSpeed = 1;//used to change how fast robot turns

    private double speedMultiplier = 1;

    private boolean busy = false;
    private double turnOffset = 0;

    private int payloadController = 2;

    @Override
    public void init() {
        control = new CuriosityUltimateGoalControl(this, true, true, true);
        control.Init();

        controllerInput1 = new ControllerInput(gamepad1, 1);
        controllerInput1.addListener(this);
        controllerInput2 = new ControllerInput(gamepad2, 2);
        controllerInput2.addListener(this);

        telemetry.addData("Speed Multiplier", speedMultiplier);
        telemetry.update();
    }

    @Override
    public void start(){control.Start();}

    @Override
    public void loop() {
        controllerInput1.Loop();
        controllerInput2.Loop();

        if(!busy) {
            ManageDriving();
        }

    }

    private void ManageDriving() {
        double moveX = -gamepad1.left_stick_y*driveSpeed*speedMultiplier;
        double moveY = -gamepad1.left_stick_x*driveSpeed*speedMultiplier;
        double turn = gamepad1.right_stick_x*turnSpeed*speedMultiplier + turnOffset;
        control.GetOrion().MoveRaw(moveX, moveY, turn);
    }

    @Override
    public void APressed(double controllerNumber) {
        if(controllerNumber == 1) {
            if (speedMultiplier == 1) speedMultiplier = 0.5;
            else if (speedMultiplier == 0.5) speedMultiplier = 0.25;
            else speedMultiplier = 1;
        }
    }

    @Override
    public void BPressed(double controllerNumber) {

    }

    @Override
    public void XPressed(double controllerNumber) {
        if(controllerNumber == payloadController){
            control.RotateStarpathToNextPos();
        }
    }

    @Override
    public void YPressed(double controllerNumber) {

    }

    @Override
    public void AHeld(double controllerNumber) {

    }

    @Override
    public void BHeld(double controllerNumber) {
        if(controllerNumber == 1){
            busy = true;
            control.TurnTowardsVuMark();
            telemetry.addLine("Turning To VuMark!");
            control.GetOrion().PrintVuforiaTelemetry(0);
            telemetry.update();
        }
    }

    @Override
    public void XHeld(double controllerNumber) {
    }

    @Override
    public void YHeld(double controllerNumber) {
        if(controllerNumber==payloadController) control.ShooterRoutine();
    }

    @Override
    public void AReleased(double controllerNumber) {

    }

    @Override
    public void BReleased(double controllerNumber)  {
        if(controllerNumber == 1) busy = false;
    }

    @Override
    public void XReleased(double controllerNumber) {
        if(controllerNumber == payloadController) busy = false;
    }

    @Override
    public void YReleased(double controllerNumber) {
        if(controllerNumber==payloadController) control.StopShooter();
    }

    @Override
    public void LBPressed(double controllerNumber) {

    }

    @Override
    public void RBPressed(double controllerNumber) {

    }

    @Override
    public void LTPressed(double controllerNumber) {

    }

    @Override
    public void RTPressed(double controllerNumber) {

    }

    @Override
    public void LBHeld(double controllerNumber) {

    }

    @Override
    public void RBHeld(double controllerNumber) {
        if(controllerNumber == payloadController) control.ShooterOn();
    }

    @Override
    public void LTHeld(double controllerNumber) {
        if(controllerNumber == payloadController) control.Intake();
    }

    @Override
    public void RTHeld(double controllerNumber) {
        if(controllerNumber == payloadController){
            control.LoadStarpath();
        }
    }

    @Override
    public void LBReleased(double controllerNumber) {

    }

    @Override
    public void RBReleased(double controllerNumber) {
        if(controllerNumber == payloadController) control.ShooterOff();
    }

    @Override
    public void LTReleased(double controllerNumber) {
        if(controllerNumber == payloadController) control.StopIntake();
    }

    @Override
    public void RTReleased(double controllerNumber) {
        if(controllerNumber == payloadController){
            control.StopLoadStarpath();
        }
    }
}
