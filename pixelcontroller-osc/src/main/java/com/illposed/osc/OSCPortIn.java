/*
 * Copyright (C) 2004-2006, C. Ramakrishnan / Illposed Software.
 * All rights reserved.
 *
 * This code is licensed under the BSD 3-Clause license.
 * See file LICENSE (or LICENSE.html) for more information.
 */

package com.illposed.osc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.SocketException;

import com.illposed.osc.utility.OSCByteArrayToJavaConverter;
import com.illposed.osc.utility.OSCPacketDispatcher;

/**
 * OSCPortIn is the class that listens for OSC messages.
 *
 * An example based on
 * {@link com.illposed.osc.OSCPortTest#testReceiving()}:
 * <pre>

	receiver = new OSCPortIn(OSCPort.DEFAULT_SC_OSC_PORT());
	OSCListener listener = new OSCListener() {
		public void acceptMessage(java.util.Date time, OSCMessage message) {
			System.out.println("Message received!");
		}
	};
	receiver.addListener("/message/receiving", listener);
	receiver.startListening();

 * </pre>
 *
 * Then, using a program such as SuperCollider or sendOSC, send a message
 * to this computer, port {@link #DEFAULT_SC_OSC_PORT},
 * with the address "/message/receiving".
 *
 * @author Chandrasekhar Ramakrishnan
 */
public class OSCPortIn extends OSCPort implements Runnable {

	// state for listening
	private boolean listening;
	private int bufferSize;
	private OSCByteArrayToJavaConverter converter
			= new OSCByteArrayToJavaConverter();
	private OSCPacketDispatcher dispatcher = new OSCPacketDispatcher();

	/**
	 * Create an OSCPort that listens on the specified port.
	 * @param port UDP port to listen on.
	 * @throws SocketException
	 */
	public OSCPortIn(int port, int bufferSize) throws SocketException {
		super(new DatagramSocket(port), port);
		this.bufferSize = bufferSize;
	}

	/**
	 * Run the loop that listens for OSC on a socket until
	 * {@link #isListening()} becomes false.
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		byte[] buffer = new byte[bufferSize];
		DatagramPacket packet = new DatagramPacket(buffer, bufferSize);
		DatagramSocket socket = getSocket();
		
		System.out.println("enter mainloop");				
		
		while (listening) {
			try {
				try {
					socket.receive(packet);
				} catch (SocketException ex) {
					if (listening) {
						throw ex;
					} else {
						// if we closed the socket while receiving data,
						// the exception is expected/normal, so we hide it
						continue;
					}
				}

				OSCPacket oscPacket = converter.convert(buffer, packet.getLength());
				dispatcher.dispatchPacket(oscPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Start listening for incoming OSCPackets
	 */
	public void startListening() {
		listening = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stop listening for incoming OSCPackets
	 */
	public void stopListening() {
		listening = false;
	}

	/**
	 * Am I listening for packets?
	 */
	public boolean isListening() {
		return listening;
	}

	/**
	 * Register the listener for incoming OSCPackets addressed to an Address
	 * @param anAddress  the address to listen for. The address can be specified as a regex, e.g., "/m.*e/receiving"
	 * @param listener   the object to invoke when a message comes in
	 */
	public void addListener(String anAddress, OSCListener listener) {
		dispatcher.addListener(anAddress, listener);
	}
	
	public void addAllListener(OSCListener listener) {
		dispatcher.addAllListener(listener);
	}
}
