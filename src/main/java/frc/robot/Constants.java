/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstrants {
        public static final int leftMotor = 3;
        public static final int rightMotor = 1;
        public static final int leftMotor2 = 2;
        public static final int rightMotor2 = 0;
    }

    public static final class ShooterConstrants {
        public static final int shooterMotorMaster = 4;
        public static final int shooterMotorSlave = 5;

        public static final int ballStorageMotor = 6;

        public static final int hoodMotor = 7;
    }

    public static final class OIConstrants {
        public static final int DriverController = 0;
    }
}
