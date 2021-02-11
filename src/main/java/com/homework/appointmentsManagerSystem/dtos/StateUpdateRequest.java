package com.homework.appointmentsManagerSystem.dtos;

import com.homework.appointmentsManagerSystem.entities.enums.State;

public class StateUpdateRequest {
    private State state;

    public StateUpdateRequest(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
