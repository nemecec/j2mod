package com.ghgande.j2mod.modbus.serial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.microedition.io.CommConnection;
import javax.microedition.io.Connector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MeSerialPort implements ModSerialPort {

  private static final Logger log = LoggerFactory.getLogger(MeSerialPort.class);

  private final String portName;

  private int baudRate;
  private int numDataBits;
  private int numStopBits;
  private int parity;
  private int flowControl;

  private int timeoutMode;
  private int readTimeout;
  private int writeTimeout;

  private CommConnection cc;
  private InputStream is;
  private OutputStream os;

  public MeSerialPort(String portName) {
    this.portName = portName;
  }

  public boolean openPort() {
    String connectorString = buildConnectorString();
    try {
      this.cc = (CommConnection) Connector.open(connectorString);
      this.is = cc.openInputStream();
      this.os = cc.openOutputStream();
    }
    catch (IOException e) {
      log.error("Could not open port {}", connectorString, e);
      return false;
    }
    return true;
  }

  private String buildConnectorString() {
    StringBuffer sb = new StringBuffer(127);
    sb.append("comm:").append(this.portName);
    sb.append(";baudrate=").append(this.baudRate);
    sb.append(";bitsperchar=").append(this.numDataBits);
    sb.append(";stopbits=").append(this.numStopBits);
    sb.append(";parity=").append(getParityAsString());
    sb.append(";autocts=").append(getCtsAsString());
    sb.append(";autorts=").append(getRtsAsString());
    return sb.toString();
  }

  private String getRtsAsString() {
    return (ModSerialPort.FLOW_CONTROL_RTS_ENABLED & this.flowControl) != 0 ? "on" : "off";
  }

  private String getCtsAsString() {
    return (ModSerialPort.FLOW_CONTROL_CTS_ENABLED & this.flowControl) != 0 ? "on" : "off";
  }

  private String getParityAsString() {
    switch (this.parity) {
    case ModSerialPort.EVEN_PARITY:
      return "even";
    case ModSerialPort.ODD_PARITY:
      return "odd";
    case ModSerialPort.NO_PARITY:
      return "none";
    default:
      throw new IllegalArgumentException("Unknown parity value: " + this.parity);
    }
  }

  public void closePort() {
    try {
      is.close();
    }
    catch (IOException e) {
      log.debug("Error while closing input stream", e);
    }
    try {
      os.close();
    }
    catch (IOException e) {
      log.debug("Error while closing output stream", e);
    }
    try {
      cc.close();
    }
    catch (IOException e) {
      log.debug("Error while closing port {}", buildConnectorString(), e);
    }
    this.is = null;
    this.os = null;
    this.cc = null;
  }

  public InputStream getInputStream() {
    return is;
  }

  public boolean isOpen() {
    return this.cc != null;
  }

  public String getDescriptivePortName() {
    return buildConnectorString();
  }

  public int readBytes(byte[] buffer, long len) {
    try {
      return is.read(buffer, 0, (int) len);
    }
    catch (IOException e) {
      log.error("Error while reading bytes", e);
      return -1;
    }
  }

  public int writeBytes(byte[] buffer, long bytesToWrite) {
    try {
      os.write(buffer, 0, (int) bytesToWrite);
      return (int) bytesToWrite;
    }
    catch (IOException e) {
      log.error("Error while writing bytes", e);
      return -1;
    }
  }

  public int bytesAvailable() {
    try {
      return is.available();
    }
    catch (IOException e) {
      log.error("Error while checking bytes available", e);
      return -1;
    }
  }

  public void setComPortParameters(int baudRate, int databits, int stopbits, int parity) {
    this.baudRate = baudRate;
    this.numDataBits = databits;
    this.numStopBits = stopbits;
    this.parity = parity;
  }

  public int getBaudRate() {
    return baudRate;
  }

  public int getNumDataBits() {
    return numDataBits;
  }

  public int getNumStopBits() {
    return numStopBits;
  }

  public int getParity() {
    return parity;
  }

  public void setFlowControl(int flowControl) {
    this.flowControl = flowControl;
  }

  public void setBaudRate(int baud) {
    this.baudRate = baud;
  }

  public void setComPortTimeouts(int timeoutMode, int readTimeout, int writeTimeout) {
    this.timeoutMode = timeoutMode;
    this.readTimeout = readTimeout;
    this.writeTimeout = writeTimeout;
  }

}
