/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Navigation.Tensorflow;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TensorFlowObjectDetector
{
    //TODO: get new objects for Ultimate Goal when the come out
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    //TODO: calibrate!
    private float cameraDistanceVar = 1;

    private static final String VUFORIA_KEY =
            "AeZ+Eyv/////AAABmfcFKgZ5NkXfgqEeyUnIJMIHuzBJhNTY+sbZO+ChF7mbo1evegC5fObZ830PRBTGTM6jbp+" +
                    "1XXCzx3XhY1kaZevEQXNpAKhXU9We0AMlp1mhnAUjGI2sprJZqJIfFGxkK598u8Bj3qQy4+PlCrk+Od" +
                    "/tAGs8dqAAsZPp4KpczFQttxMBC5JZNeIbIFP57InXOeJgyeH1sXK+R2i6nPfOFRvHJjdQaLWdAasv7" +
                    "i3b0RH5ctG7Ky7J9g9BPYI03kkChCJkbPg03XnoqCcC7rEpAk3n8a9CqtwTUu57Sy0jCDUd2O6X9kHj" +
                    "Z5ZmS0I3O0YSzX3Jp2ppTE2kDS2I9zBYEmuEqkMjItxd52oES0Ij0rZm";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private OpMode opMode;

    public TensorFlowObjectDetector(OpMode setOpMode, VuforiaLocalizer setVuforia){
        //Set opmode and vuforia instance
        opMode = setOpMode;
        vuforia = setVuforia;

        //Init
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            opMode.telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        if (tfod != null)tfod.activate();
    }

    //Creates a list of detected objects
    public List<Recognition> GetRecognitions() {
        if (tfod != null) {
            List<Recognition> recognitions = tfod.getRecognitions();

            //TODO: is this telemetry necassary?
            if (recognitions != null) {
                opMode.telemetry.addData("# Object Detected", recognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;
                for (Recognition recognition : recognitions) {
                    opMode.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    opMode.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    opMode.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom());
                }
                opMode.telemetry.update();
            }
            return recognitions; //TODO: figure out what units this is and whether its screen or world space
        }
        else return null;
    }

    //Starts up tensorflow
    private void initTfod() {
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
       tfodParameters.minimumConfidence = 0.8;
       tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
       tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    //Shuts down tensorflow
    public void stopTfod(){
        if (tfod != null) tfod.shutdown();
    }

    ////PUBLIC METHODS////
    //Returns coords of the closest disc
    public double[] GetClosestDisc(){ //Find object by its y distance from the robot
        List<Recognition> objs = GetRecognitions(); //find all objects
        double x = 1000;
        double y = 1000;
        if(objs != null) {
            for (Recognition obj : objs) {
                if (obj.getLabel() == LABEL_FIRST_ELEMENT) { //get all discs
                    //Find x and y of disc
                    double discX = (obj.getRight() + obj.getLeft()) / 2;
                    double discY = (obj.getTop() + obj.getBottom()) / 2;
                    //if current dist is less than highest dist
                    if(discY < y){
                        //set lowest values as these
                        x = discX;
                        y = discY;
                    }
                }
            }
        }
        return new double[] {x,y};
    }

    //Returns distance and angle of closest disc
    public double[] GetClosestDiscDistAngle(){ //TODO: get working
        double[] offset = GetClosestDisc();
        return offset;
    }
}
