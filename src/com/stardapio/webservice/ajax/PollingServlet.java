package com.stardapio.webservice.ajax;

import org.codehaus.jettison.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class PollingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -363878471390606821L;
	
	private final Random random = new Random();
	
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
    
    private final Thread generator = new Thread("Event generator") {    	
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                messages.offer("At " + new Date());
            }
        }
    };

    @Override
    public void init() throws ServletException {
        generator.start();
    }

    @Override
    public void destroy() {
        generator.interrupt();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> messages = new LinkedList<String>();
        this.messages.drainTo(messages);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(new JSONArray(messages).toString());
        resp.getWriter().flush();
    }
}