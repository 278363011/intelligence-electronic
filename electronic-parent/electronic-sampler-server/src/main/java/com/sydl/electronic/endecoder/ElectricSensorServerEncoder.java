package com.sydl.electronic.endecoder;

import com.sydl.electronic.constants.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ElectricSensorServerEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

        // 要发送的数据
        // 这里如果是自定义的类型，msg即为自定义的类型，需要转为byte[]
        byte[] body = ((ByteBuf)msg).array();

        // 数据长度
        int dataLength = body.length;

        //包头，确定数据包的开始
        out.writeInt(ConstantValue.FLAG);

        //module
        out.writeShort(ConstantValue.MODEL);
        //cmd
        out.writeShort(ConstantValue.ORDER);

        // 缓冲区先写入数据长度
        out.writeInt(dataLength);
        // 再写入数据
        out.writeBytes(body);



        System.out.println("消息发送完毕");

    }
}
