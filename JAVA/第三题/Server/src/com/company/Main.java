package com.company;

import Socket.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Server().service();
    }
}
