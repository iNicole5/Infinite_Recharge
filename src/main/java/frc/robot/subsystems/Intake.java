/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// True = open = NO all touching
//False = close = ball touching

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;

public class Intake extends SubsystemBase {

  TalonSRX ballStorage;
  VictorSPX intake;

  Encoder ballStorageEncoder;

  DigitalInput bottomSwitch;
  DigitalInput middleSwitch;
  DigitalInput topSwitch;

  public Intake() {
    ballStorage = new TalonSRX(Constants.IntakeConstrants.ballStorageMotor);
    intake = new VictorSPX(Constants.IntakeConstrants.intake);

    bottomSwitch = new DigitalInput(1);
    middleSwitch = new DigitalInput(2);
    topSwitch = new DigitalInput(3);

    initBallStorageEncoder();
  }

  public void initBallStorageEncoder()
  {
    ballStorageEncoder = new Encoder(14, 15, false, EncodingType.k4X);
    ballStorageEncoder.setSamplesToAverage(5);
    ballStorageEncoder.setDistancePerPulse(1./256.);
    ballStorageEncoder.setReverseDirection(true);
    ballStorageEncoder.reset();
  }

  public void resetBallStorageEncoder()
  {
    ballStorageEncoder.reset();
  }

  public boolean getTopSwitch()
  {
    return !topSwitch.get();
  }

  public boolean getMiddleSwitch()
  {
    return !middleSwitch.get();
  }

  public boolean getBottomSensor()
  {
    return !bottomSwitch.get();
  }

  public double getBallStorageEncoder() {
    return ballStorageEncoder.getDistance();
  }

  public void turnBallStorage(double turn)
  {
    ballStorage.set(ControlMode.PercentOutput, turn);
  }

  public void stopBallStorage()
  {
    turnBallStorage(0);
  }


// True = open = NO ball touching = No light
//False = close = ball touching  = Red Light  == !
/*  
public void ballStorageMovement()
  {
    double numBalls = 0;
    if(bottomSwitch.get() && !topSwitch.get())
    {
      numBalls = numBalls + 1643;
      double numRev = ballStorageEncoder.getDistance();
      while (numRev < 1643)  //1643
      {
        numRev = ballStorageEncoder.getDistance();
        turnBallStorage(.25);
        System.out.println("Move Motor");
      }
        stopBallStorage();
        numBalls =  numRev + 1643;
        System.out.println("StopMotor");
    } 
    else
    {
      turnBallStorage(0);
      System.out.println("else Stop Motor");
    } 
  }
*/
  int numBalls = 0;
  public void indexBall()
  {
  
   /* double numRev = ballStorageEncoder.getDistance();
    System.out.println(numRev);
    if (numRev < 1643 * numBalls)
    {
      numRev = ballStorageEncoder.getDistance();
      turnBallStorage(.25);
    }else{
      stopBallStorage();
    }
    */
  }

  public void runBalls()
  {
    turnBallStorage(.5);
  }

  public void incrementBalls()
  {
    if(!bottomSwitch.get())   //light on motor on
    {                         //light off motor off
      turnBallStorage(.5);
    } 
     else if (!topSwitch.get())
    {
      stopBallStorage();
    }  
    else 
    {
      stopBallStorage();
    }
      /*
      if (topSwitch.get())
      {
        stopBallStorage();
      }
      */
  }
  
  public void increaseBalls()
  {
    numBalls = numBalls + 1;
  }

  public void floorIntake(boolean direction)
  {
    if (direction)
    {
      intake.set(ControlMode.PercentOutput, 1);
    } else {
      intake.set(ControlMode.PercentOutput, -1);
    }
  }

  public void intakeStop()
  {
    intake.set(ControlMode.PercentOutput, 0);
  }

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Ball Encoder", getBallStorageEncoder());

  }
}
