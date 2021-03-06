package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.openftc.revextensions2.ExpansionHubEx;

/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@Disabled
@Autonomous
public class RingDetectorTest extends LinearOpMode {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor spinnyThing; //aka slow motor
    public DcMotor fasterSpinnyThing; //aka 6k rpm motor
    //public DcMotor waitWeNeedAnotherMotor; //oh come on, don't force me to give them names
    ExpansionHubEx expansionHub;
    public Servo grabber; //who said we needed to give them normal names?
    public Servo grabNFlip; //seriously, you thought I would name this better?
    //public Servo IDK; //since when do I have to give them all proper names?
    //public Servo angler; //It just angles the launcher mechanism

    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        //spinnyThing =hardwareMap.dcMotor.get("intake");
        //fasterSpinnyThing=hardwareMap.dcMotor.get("shooter");
        //grabber=hardwareMap.servo.get("grabber";
        //grabNFlip=hardwareMap.servo.get("flipper");
        //angler=hardwareMap.servo.get("angler");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {//just flash LED's for now
            if(CameraEx.ringCount==4) {
                noRings();
            }
            else if(CameraEx.ringCount==1){
                oneRing();
            }
            else{
                fourRings();
            }
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();

        }
    }

    public void noRings(){
        //no rings visible
        expansionHub.setLedColor(255, 0, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }
    public void oneRing(){
        //tells if one ring
        expansionHub.setLedColor(0, 255, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }
    public void fourRings(){
        //says there are 4 rings
        expansionHub.setLedColor(0, 0, 255);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }

}
