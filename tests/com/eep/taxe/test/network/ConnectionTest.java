package com.eep.taxe.test.network;

import static org.junit.Assert.*;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Awaitility.*;
import com.jayway.awaitility.Duration.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.GameClient;

public class ConnectionTest {

	private GameClient client;
	
	private final int timeout = 5;
	
	
	@Before
	public void setUp() throws Exception {
		client = new GameClient();
	}

	@Test
	public void test() {
		client.connect();
		Awaitility.await().atMost(timeout, TimeUnit.SECONDS).until(new Callable<Boolean>() {@Override
			public Boolean call() throws Exception {
				return client.isConnected();
			}
		});
		assertTrue("Server is offline or connection failed", client.isConnected());
	}

}
