/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.cmdAutoLine;
import frc.robot.commands.cmdClimber;
import frc.robot.commands.cmdDriveTrain;
import frc.robot.commands.cmdHood;
import frc.robot.commands.cmdHookLeveler;
import frc.robot.commands.cmdIntake;
import frc.robot.commands.cmdIntermediate;
import frc.robot.commands.cmdShoot;
import frc.robot.commands.cmdTestEncoder;
import frc.robot.commands.cmdballStorage;
import frc.robot.commands.limelightDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private Joystick Driver = new Joystick(0);
  private Joystick Driver2 = new Joystick(1);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber();

  private final cmdAutoLine autoCommand = new cmdAutoLine(driveTrain, 4.5, 0.5);  //Duration, speed

  private final limelightDrive limelightDrive = new limelightDrive(driveTrain);
  private final cmdballStorage ballStorage = new cmdballStorage(intake);
 // private final cmdTestEncoder testRevolutions = new cmdTestEncoder(intake);
 private final cmdIntermediate intermediateIntake = new cmdIntermediate(intake);
  private final cmdIntake CMDintake = new cmdIntake(intake, false);
  private final cmdShoot Shoot = new cmdShoot(shooter, intake, false);
  private final cmdHood HoodForward = new cmdHood(shooter, true);
  private final cmdHood HoodBack = new cmdHood(shooter, false);
  private final cmdClimber cmdClimberUP = new cmdClimber(climber, true);
  private final cmdClimber cmdClimberDown = new cmdClimber(climber, false);
  private final cmdHookLeveler Hookleveler = new cmdHookLeveler(true, climber); 


  private Trigger bottomLimit = new Trigger(() -> intake.getBottomSensor());



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveTrain.setDefaultCommand(new cmdDriveTrain(driveTrain, Driver));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton runAutoLineButton = new JoystickButton(Driver, 1);
    runAutoLineButton.whenPressed(new cmdAutoLine(driveTrain, 2, 0.4));

    JoystickButton runLimelight = new JoystickButton(Driver, 2);
    runLimelight.whileHeld(limelightDrive);

    // JoystickButton runIntake = new JoystickButton(Driver, 3);
    // runIntake.whileHeld(ballStorage);

    JoystickButton middleIntake = new JoystickButton(Driver, 4);
    bottomLimit.whenActive(intermediateIntake, false);

    JoystickButton floorIntake = new JoystickButton(Driver, 5);
    floorIntake.whileHeld(CMDintake, true);

    JoystickButton fire = new JoystickButton(Driver, 6);
    fire.whileHeld(Shoot, true);

    JoystickButton hoodForward = new JoystickButton(Driver, 7);
    hoodForward.whileHeld(HoodForward, true);

    JoystickButton hoodBack = new JoystickButton(Driver, 8);
    hoodBack.whileHeld(HoodBack, true);

    JoystickButton irrectionUp = new JoystickButton(Driver, 9);
    irrectionUp.whileHeld(cmdClimberUP, true);

    JoystickButton irrectionDown = new JoystickButton(Driver, 10);
    irrectionDown.whileHeld(cmdClimberDown, true);

    JoystickButton testmultipleAuto = new JoystickButton(Driver2, 1);
    //testmultipleAuto.whenPressed(driveTrain.moveForward(andThen(driveTrain.moveBack())));

    JoystickButton levelerButtonRight = new JoystickButton(Driver2, 3);
    levelerButtonRight.whileHeld(Hookleveler, true);

    JoystickButton levelerButtonLeft = new JoystickButton(Driver2, 4);
    levelerButtonLeft.whileHeld(Hookleveler, false);



    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
