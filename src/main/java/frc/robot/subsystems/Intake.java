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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  TalonSRX ballStorage;

  Encoder ballStorageEncoder;

  DigitalInput bottomSwitch;
  DigitalInput topSwitch;

  public Intake() {
    ballStorage = new TalonSRX(Constants.ShooterConstrants.ballStorageMotor);

    bottomSwitch = new DigitalInput(1);
    topSwitch = new DigitalInput(2);

    ballStorageEncoder.setDistancePerPulse(25000);

    initBallStorageEncoder();
  }

  public void initBallStorageEncoder()
  {
    ballStorageEncoder = new Encoder(12, 13, false, EncodingType.k4X);
    ballStorageEncoder.setSamplesToAverage(5);
    ballStorageEncoder.reset();
  }

  public void resetBallStorageEncoder()
  {
    ballStorageEncoder.reset();
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
//test
// True = open = NO ball touching = No light
//False = close = ball touching  = Red Light  == !
  public void ballStorageMovement()
  {
    if(bottomSwitch.get() && !topSwitch.get())
    {
      turnBallStorage(.25);
      System.out.println("Motors Move 1");
    } else if (bottomSwitch.get() && topSwitch.get()) 
    { 
      turnBallStorage(0);
      System.out.println("NO Motors move");
    } else
    {
      turnBallStorage(0);
    } 
  }

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Ball Encoder", getBallStorageEncoder());

  }
}
