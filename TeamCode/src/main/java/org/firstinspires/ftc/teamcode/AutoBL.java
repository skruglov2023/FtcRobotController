package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.openftc.revextensions2.ExpansionHubEx;
/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@Disabled
@Autonomous (name = "Blue side left", group = "Autonomous stuff")
public class AutoBL extends LinearOpMode{
    /* where is the starting position (depending on which line it's starting at) (repeat until certain amount of time idk)
         sensor how many rings
             put wobble goal in square A if 0 rings & go back to launch zone
             put wobble goal in square B if 1 ring & go back to launch zone
             put wobble goal in square C if 4 rings & go back to launch zone
         sensor rings, pick up rings, and get in position to shoot
             shoot at tower 1st, 2nd, 3rd hole
             shoot at power shot 1st, 2nd, 3rd stick thing
     (when time is certain amount) sensor line and park over it
*/
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
    public void runOpMode()  {
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

        double m = .5; //speed multiplier
        double p= .5;
        waitForStart();
//-------------the following lines move the robot to some area, hopefully forward, right, then back
   /*     fl.setPower(-.5);
        fr.setPower(-.5);
        bl.setPower(-.5);
        br.setPower(-.5);
        sleep(2000);
//drop wobble
        fl.setPower(.5);
        fr.setPower(.5);
        bl.setPower(.5);
        br.setPower(.5);
        sleep(1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        */



        while (opModeIsActive()) {//just flash LED's for now
            if(CameraEx.ringCount==4) {
                BlueZoneA();
            }
            else if(CameraEx.ringCount==1){
                BlueZoneB();
            }
            else{
                BlueZoneC();
            }
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();

        }
    }

    public void BlueZoneA(){
        //make it to the target zone A
        expansionHub.setLedColor(255, 0, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }
    public void BlueZoneB(){
        //make it to the target zone B
        expansionHub.setLedColor(0, 255, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }
    public void BlueZoneC(){
        //make it to the target zone C
        expansionHub.setLedColor(0, 0, 255);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }

}
