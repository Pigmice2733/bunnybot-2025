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

    public void setIndexerOutput(double pct) {
        if (Math.abs(pct) > 1)
            return;
        upMotor.set(pct);
        lowMotor.set(pct);
    }

    public double getUpperIndexerSpeed() {
        return upMotor.get();
    }

    public double getLowerIndexerSpeed() {
        return lowMotor.get();
    }

    public Command startIndexer(boolean fwd) {
        return Commands
                .runOnce(() -> setIndexerOutput(fwd ? IndexerConfig.INDEXER_SPEED : -IndexerConfig.INDEXER_SPEED));
    }

    public Command stopIndexer() {
        return Commands.runOnce(() -> setIndexerOutput(0));
    }
}
