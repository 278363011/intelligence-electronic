package com.sydl.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerMessageHandler  extends ChannelInboundHandlerAdapter {
    private int messageCount = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * 1.读数据，这里收到的是一个完整的消息数据，在decoder的out传递到当前逻辑
         * 2.对消息进一步的解码
         * */
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("解码处理......");

        //包头，确定数据包的开始
        System.out.println("包头:"+buf.readInt());
        //module
        System.out.println("module:"+buf.readShort());
        //cmd
        System.out.println("cmd:"+buf.readShort());
        int length=buf.readInt();
        System.out.println("实体消息长度:"+length);
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        System.out.println(new String(bytes,"UTF-8"));

        System.out.println("消息总数:"+(++messageCount));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
