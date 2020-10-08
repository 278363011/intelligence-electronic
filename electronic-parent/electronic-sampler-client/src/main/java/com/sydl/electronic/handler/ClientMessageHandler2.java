package com.sydl.electronic.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientMessageHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {



        String msg = "{\"_index\":\"applog-after-2020.10.06\",\"_type\":\"doc\",\"_id\":\"ypCR-3QBws8Tz68ZfEZt\",\"_score\":null,\"_source\":{\"class\":\"Microsoft.AspNetCore.Mvc.Internal.ControllerActionInvoker\",\"beat\":{\"hostname\":\"logstistic-customer-128-191\",\"name\":\"logstistic-customer-128-191\",\"version\":\"6.6.0\"},\"log\":{\"file\":{\"path\":\"/home/xsyx-aftersales-returnbill-api/logs/Info/20201006.log\"}},\"currentDateTime\":\"2020-10-06T09:40:47.948+08:00\",\"location\":\"?,?,?,?\",\"@timestamp\":\"2020-10-06T01:40:48.047Z\",\"index_time\":\"2020.10.06\",\"offset\":42674155,\"hostName\":\"logstistic-customer-128-191\",\"host\":{\"name\":\"logstistic-customer-128-191\"},\"message\":\"[2020-10-06T09:40:47.948+08:00] [INFO] [42-42id] [Microsoft.AspNetCore.Mvc.Internal.ControllerActionInvoker] [logstistic-customer-128-191] [10.8.128.191] [xsyx-aftersales-returnbill-api] [] [] [42544] [?,?,?,?] [Executing action method XSYX.Basic.WebApi.StatusController.GetStatus (XSYX.Basic) - Validation state: Valid] ## ''\",\"thread-id\":\"42-42id\",\"source\":\"/home/xsyx-aftersales-returnbill-api/logs/Info/20201006.log\",\"messageInfo\":\"Executing action method XSYX.Basic.WebApi.StatusController.GetStatus (XSYX.Basic) - Validation state: Valid\",\"fields\":{\"evn\":\"dev\",\"logbiz\":\"after\",\"logtopic\":\"app-log-xsyx-logistics-aftersales-pro\"},\"level\":\"INFO\",\"ip\":\"10.8.128.191\",\"applicationName\":\"xsyx-aftersales-returnbill-api\",\"eventId\":\"42544\",\"@version\":\"1\"},\"sort\":[1601948447948]}";
        byte[] data;
        ByteBuf buf;
        for (int i=0;i<1000;i++) {
            data = (msg+i).getBytes();
            buf = Unpooled.copiedBuffer(data);
            ctx.writeAndFlush(buf);
        }

        System.out.println("1条 消息发送完毕");
//        ctx.close();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
