/*
 * Copyright 2002-2016 jamod & j2mod development teams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ghgande.j2mod.modbus.io;

import com.ghgande.j2mod.modbus.msg.ModbusMessage;
import com.ghgande.j2mod.modbus.msg.ModbusRequest;
import com.ghgande.j2mod.modbus.msg.ModbusResponse;
import com.ghgande.j2mod.modbus.serial.ModSerialPort;

/**
 * Any class that wants to listen for the begining and ending of read/writes
 * to the Serial channel need to implement this interface
 *
 * @author Steve O'Hara (4energy)
 * @version 2.0 (March 2016)
 */
abstract public class AbstractSerialTransportListener {

    /**
     * Will be called whenever a message is about to be written
     *
     * @param port Port being used
     * @param msg  Message to be written
     */
    public void beforeMessageWrite(ModSerialPort port, ModbusMessage msg) {
    }

    /**
     * Will be called whenever a message has been written
     *
     * @param port Port being used
     * @param msg  Message written
     */
    public void afterMessageWrite(ModSerialPort port, ModbusMessage msg) {
    }

    /**
     * Called before a request is read
     *
     * @param port Port to read
     */
    public void beforeRequestRead(ModSerialPort port) {
    }

    /**
     * Called whenever a request has been received
     *
     * @param port Port to read
     * @param req  Request received
     */
    public void afterRequestRead(ModSerialPort port, ModbusRequest req) {
    }

    /**
     * Called before a response is read
     *
     * @param port Port to read
     */
    public void beforeResponseRead(ModSerialPort port) {
    }

    /**
     * Called whenever a response has been received
     *
     * @param port Port to read
     * @param res  Response received
     */
    public void afterResponseRead(ModSerialPort port, ModbusResponse res) {
    }
}
