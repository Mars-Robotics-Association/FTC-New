package org.firstinspires.ftc.teamcode.MechanicalControl.Curiosity;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class CuriosityPayloadController
{
    ////DEPENDENCIES////
    OpMode opMode;
    //Motors
    private DcMotor[] shooterMotors;
    //Servos
    private Servo intakeServo1;
    private Servo intakeServo2;
    private Servo bootServo;
    private Servo starpathServo;
    private Servo loaderServo;
    //Sensors

    ////CONFIG VARIABLES////
    public static double bootInPos = 0.65;
    public static double bootOutPos = 0.45;
    public static double starpathDownPos = 0;
    public static double starpathInterval = 0.17;
    public static double starpathUpPos = 0.32;
    public static double loaderClearPos = 0.5;
    public static double loaderLoadPos = 1;


    ////PRIVATE VARIABLES////
    private double shooterStartTime = 0;
    private boolean shooterRunning = false;
    private double loaderStartTime = 0;
    private boolean loadFromIntakeRunning = false;
    private int starpathPosition = 0; //starts at 0, 3 is to the shooter, 5 is max
    private boolean starpathUsed = false;
    public boolean loaderUsed = false;
    public boolean bootUsed = false;

    public void Init(OpMode setOpMode, DcMotor[] setShooterMotors, Servo setIntakeServo1, Servo setIntakeServo2, Servo setBootServo, Servo setStarpathServo, Servo setLoaderServo){
        opMode = setOpMode;

        shooterMotors = setShooterMotors;

        intakeServo1 = setIntakeServo1;
        intakeServo2 = setIntakeServo2;
        bootServo = setBootServo;
        starpathServo = setStarpathServo;
        loaderServo = setLoaderServo;

    }

    private void SetShooterPower(double power){
        int i = 0;
        for (DcMotor motor: shooterMotors) {
            motor.setPower(power);
            i++;
        }
    }

    private void BootDisc(){bootServo.setPosition(bootInPos);}
    private void BootMiddle(){bootServo.setPosition((bootOutPos+bootInPos)/2);}
    private void BootReset(){bootServo.setPosition(bootOutPos);}

    private void StarpathToIntake(){starpathServo.setPosition(starpathDownPos);}
    private void StarpathMoveInterval(){starpathServo.setPosition(starpathServo.getPosition()+starpathInterval);}
    private void StarPathToShooter(){starpathServo.setPosition(starpathUpPos);}

    private void LoaderClear(){loaderServo.setPosition(loaderClearPos);}
    private void LoaderLoad(){loaderServo.setPosition(loaderLoadPos);}

    public void ShooterOn(){SetShooterPower(-1);}
    public void ShooterOff(){SetShooterPower(0);}

    public void RotateStarpathToNextPos(){
        starpathPosition++;
        //if next pos is 6, reset back to 0
        if(starpathPosition == 6){
            starpathPosition = 0;
            StarpathToIntake();
        }
        //if next pos is 3, go to the shooter
        else if(starpathPosition == 3)StarPathToShooter();
        //else, add with the interval
        else StarpathMoveInterval();
    }

    public void Intake(){
        /*//if starpath not at intake, return it to intake
        if(starpathPosition > 2 && !loadFromIntakeRunning){
            starpathPosition = 0;
            StarpathToIntake();
        }*/
        //run intake
        intakeServo1.setPosition(1);
        intakeServo2.setPosition(0);
        if(!loadFromIntakeRunning) BootReset();
    }
    public void StopIntake(){
        intakeServo1.setPosition(0.5);
        intakeServo2.setPosition(0.5);
    }

    public void LoadFromIntake(){
        //start timer
        if(!loadFromIntakeRunning)loaderStartTime = opMode.getRuntime();
        loadFromIntakeRunning = true;

        //boot disc into starpath
        if(!bootUsed) BootDisc();

        //wait
        if(loaderStartTime+1 > opMode.getRuntime()) return;
        if(starpathPosition == 0 || starpathPosition == 1) BootReset();
        else BootMiddle();
        bootUsed = true;

    }
    public void StopLoadFromIntake(){
        //rotate starpath to next pos
        if(!starpathUsed) RotateStarpathToNextPos();
        starpathUsed = true;
        bootUsed = false;
        loadFromIntakeRunning = false;
        starpathUsed = false;
    }

    public void Shoot(){
        //start timer
        if(!shooterRunning)shooterStartTime = opMode.getRuntime();
        shooterRunning = true;

        //rotate starpath to shooter if not there
        if(starpathPosition < 3){
            starpathPosition = 3;
            StarPathToShooter();
        }

        //spinup shooter
        ShooterOn();

        //wait
        if(shooterStartTime+2 > opMode.getRuntime()) return;

        //load shooter -> it shoots
        if(!loaderUsed)LoaderLoad();
        loaderUsed = true;

        //wait
        if(shooterStartTime+3 > opMode.getRuntime()) return;

        //retract loader
        LoaderClear();

        //wait
        if(shooterStartTime+4 > opMode.getRuntime()) return;

        //stop shooter
        ShooterOff();

        //rotate starpath to next pos
        //if(!starpathUsed)RotateStarpathToNextPos();
        //starpathUsed = true;
    }
    public void StopShooter(){
        ShooterOff();
        LoaderClear();
        shooterRunning = false;
        starpathUsed = false;
        loaderUsed = false;
    }

}
