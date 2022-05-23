package org.loose.fis.sre.exceptions;

public class AgentDoesNotExistException extends Exception {


    private String agent_book;

    public AgentDoesNotExistException(String agent_book) {
        super(String.format("An agent with the name %s does not exist!", agent_book));
        this.agent_book = agent_book;

    }

    public String getAgent_book() {
        return agent_book;
    }


}
