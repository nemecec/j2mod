package com.ghgande.j2mod.modbus.serial;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;

public class JSerialPort implements ModSerialPort {

  private SerialPort serialPort;

  public JSerialPort(String portName) {
    this.serialPort = SerialPort.getCommPort(portName);
  }

  public SerialPort getDelegate() {
    return this.serialPort;
  }

  @Override
  public boolean openPort() {
    return serialPort.openPort();
  }

  @Override
  public void closePort() {
    serialPort.closePort();
  }

  @Override
  public InputStream getInputStream() {
    return serialPort.getInputStream();
  }

  @Override
  public boolean isOpen() {
    return serialPort.isOpen();
  }

  @Override
  public String getDescriptivePortName() {
    return serialPort.getDescriptivePortName();
  }

  @Override
  public int readBytes(byte[] buffer, long len) {
    return serialPort.readBytes(buffer, len);
  }

  @Override
  public int writeBytes(byte[] buffer, long bytesToWrite) {
    return serialPort.writeBytes(buffer, bytesToWrite);
  }

  @Override
  public int bytesAvailable() {
    return serialPort.bytesAvailable();
  }

  @Override
  public void setComPortParameters(int baudRate, int databits, int stopbits, int parity) {
    serialPort.setComPortParameters(baudRate, databits, stopbits, parity);
  }

  @Override
  public void setFlowControl(int flowControlSettings) {
    serialPort.setFlowControl(flowControlSettings);
  }

  @Override
  public int getBaudRate() {
    return serialPort.getBaudRate();
  }

  @Override
  public int getNumDataBits() {
    return serialPort.getNumDataBits();
  }

  @Override
  public int getNumStopBits() {
    return serialPort.getNumStopBits();
  }

  @Override
  public int getParity() {
    return serialPort.getParity();
  }

  @Override
  public void setComPortTimeouts(int timeoutMode, int readTimeout, int writeTimeout) {
    serialPort.setComPortTimeouts(timeoutMode, readTimeout, writeTimeout);
  }

  @Override
  public void setBaudRate(int baud) {
    serialPort.setBaudRate(baud);
  }

}
