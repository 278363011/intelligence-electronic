package com.sydl.electronic;

import com.sydl.electronic.server.NettyServer;

public class ServerApplication {

    public static void main(String[] args) {
        NettyServer nettyServer=new NettyServer();
        nettyServer.start();
    }

}
