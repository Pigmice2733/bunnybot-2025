package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.CANConfig;
import frc.robot.Constants.IndexerConfig;

public class Indexer {
    private SparkMax upMotor;
    private SparkMax lowMotor;

    public Indexer() {
        upMotor = new SparkMax(CANConfig.INDEXER_PORT, MotorType.kBrushless);
        lowMotor = new SparkMax(CANConfig.INDEXER_PORT, MotorType.kBrushless);
        SparkMaxConfig indexerConfig = new SparkMaxConfig();
        upMotor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        lowMotor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /**
     * Sets the speed of both indexer motors
     * 
     * @param pct Value between -1 and 1 for the speed to be set to
     */
    public void setIndexerSpeed(double pct) {
        if (Math.abs(pct) > 1)
            return;
        upMotor.set(pct);
        lowMotor.set(pct);
    }

    /**
     * @return Speed of the upper motor of the indexer
     */
    public double getUpperIndexerSpeed() {
        return upMotor.get();
    }

    /**
     * @return Speed of the lower motor of the indexer
     */
    public double getLowerIndexerSpeed() {
        return lowMotor.get();
    }

    /**
     * Sets the speed of both indexer motors to the default indexer speed. Can be
     * set to go forwards or backwards.
     * 
     * @param fwd Boolean value of whether the motors are running forwards or
     *            backwards
     */
    public Command startIndexer(boolean fwd) {
        return Commands
                .runOnce(() -> setIndexerSpeed(fwd ? IndexerConfig.INDEXER_SPEED : -IndexerConfig.INDEXER_SPEED));
    }

    /**
     * Sets the speed of both indexer motors to 0
     */
    public Command stopIndexer() {
        return Commands.runOnce(() -> setIndexerSpeed(0));
    }
}
