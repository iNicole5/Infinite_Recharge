/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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

public class Shooter extends SubsystemBase {

  public TalonSRX shooterMaster;
  public VictorSPX shooterSlave;

  public VictorSPX hoodMotor;

  DigitalInput hoodZeroLimitSwitch;

  public boolean shooterMode = false;

  //Encoder shooterEncoder;
  Encoder hoodEncoder;

  public Shooter() {
    shooterMaster = new TalonSRX(Constants.ShooterConstrants.shooterMotorMaster);
    shooterSlave = new VictorSPX(Constants.ShooterConstrants.shooterMotorSlave);

    hoodMotor = new VictorSPX(Constants.ShooterConstrants.hoodMotor);

    shooterSlave.follow(shooterMaster);

    hoodZeroLimitSwitch = new DigitalInput(0);

    initShooterEncoder();
    initHoodEncoder();
  }

  private void initShooterEncoder()
  {
    //shooterEncoder = new Encoder(2, 3, false, EncodingType.k4X);
    //shooterEncoder.setSamplesToAverage(5);
    //shooterEncoder.reset();
  }

  private void initHoodEncoder()
  {
    hoodEncoder = new Encoder(12, 13, false, EncodingType.k4X);
    hoodEncoder.setSamplesToAverage(5);
    hoodEncoder.reset();
  }

  // Runs the motor backwards at half speed until the limit switch is pressed
    // then turn off the motor and reset the encoder
    /*
    if(!limit.get()) {
      spark.set(-.5);
  } else {
      spark.set(0);
      encoder.reset();
  }
*/
  public void resetShooterEncoder()
  {
    //shooterEncoder.reset();
  }

  public void resetHoodEncoder()
  {
    hoodEncoder.reset();
  }
/*
  public double getShooterEncoder() {
    return shooterEncoder.getDistance();
  }
*/
  public double getHoodEncoder() {
    return hoodEncoder.getDistance();
  }

  public void hood(boolean direction)
  {
    if(direction)
    {
      hoodMotor.set(ControlMode.PercentOutput, 1);
    } else {
      hoodMotor.set(ControlMode.PercentOutput, -1);
    }
  }

  public void hoodStop()
  {
    hoodMotor.set(ControlMode.PercentOutput, 0);
  }

  public void resetHoodEncoderLimitSwitch()
  {
    if(!hoodZeroLimitSwitch.get()) 
    {
      hoodEncoder.reset();
    } 
  }

  public void hoodTargetArea()
  {

  }

  public void shoot(boolean direction)
  {
    if(direction)
    {
      shooterMaster.set(ControlMode.PercentOutput, 1);
    } else {
      shooterMaster.set(ControlMode.PercentOutput, -1);
    }
  }

  public void shooterStop()
  {
    shooterMaster.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Hood Encoder", getHoodEncoder());

  }
}
